package com.huc.service.impl;

import com.huc.bean.*;
import com.huc.common.response.RespCode;
import com.huc.common.response.RespData;
import com.huc.common.result.ResultApplyUser;
import com.huc.common.result.ResultEntryForm;
import com.huc.dao.*;
import com.huc.service.IEntryFormService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EntryFormServiceImpl implements IEntryFormService {
    @Resource
    private EntryFormMapper entryFormMapper;
    @Resource
    private MinorMapper minorMapper;
    @Resource
    private MajorMapper majorMapper;
    @Resource
    private UserMapper userMapper;

    //申请报名辅修学位
    @Override
    public RespData apply(EntryForm entryForm, String name, String name1, String username) {
        User user = userMapper.queryUserUsername(name);
        //校验是否为本人报名
        if (!username.equals(user.getUsername())){
            return new RespData(RespCode.EXTEND_MAX_FILESIZE);
        }
        if (!name.equals(name1)){
            return new RespData(RespCode.EXTEND_MAX_FILESIZE);
        }
        EntryForm apply = entryFormMapper.queryEntryFormByCondition(entryForm.getUserName(), entryForm.getMajorName(), entryForm.getMinorId());
        if (apply != null){
            entryFormMapper.insert(entryForm);//进行报名，向报名表中添加数据
            return new RespData(RespCode.SUCCESS);
        }else {
            return new RespData(RespCode.REPETITION);
        }
    }

    //检验报名辅修学位前是否登录
    @Override
    public RespData check(Integer id) {
        if (id == null || id == 0)
            return new RespData(RespCode.ERROR_SESSION);
        return new RespData(RespCode.SUCCESS);
    }

    //查询没有审核的报名数据
    @Override
    public RespData queryToAudit() {
        List<EntryForm> entryFormList = entryFormMapper.queryToAudit(1);
        if (entryFormList.isEmpty()){
            return new RespData(RespCode.SUCCESS);
        }
        List<ResultEntryForm> list = convertUserList(entryFormList);
        return new RespData(RespCode.SUCCESS,list);
    }

    //对报名用户进行审核
    @Override
    public RespData checkApply(int entryFormId) {
        entryFormMapper.checkApply(entryFormId);//对数据审核
        //审核后修改辅修课程的报名人数
        EntryForm entryForm = entryFormMapper.queryMinorId(entryFormId);
        Minor minor = minorMapper.queryCount(entryForm.getMinorId());
        minorMapper.updateCount(minor.getCount()+1,entryForm.getMinorId());
        return new RespData(RespCode.SUCCESS);
    }

    //对于报名人员的查询
    @Override
    public RespData queryApplyUser() {
        List<EntryForm> entryFormList = entryFormMapper.queryApplyUser();
        List<ResultApplyUser> userList = convertResultApplyUserList(entryFormList);
        return new RespData(RespCode.SUCCESS,userList);
    }

    //通过全部审核
    @Override
    public RespData allApply() {
        entryFormMapper.allApply();
        return new RespData(RespCode.SUCCESS);
    }

    //将待审核数据转换为我们所需要的，便于页面话处理的对象
    private List<ResultEntryForm> convertUserList(List<EntryForm> list){
        List<ResultEntryForm> entryFormList = new ArrayList<>();
        for (EntryForm entryForm : list) {
            ResultEntryForm resultEntryForm = new ResultEntryForm();
            resultEntryForm.setEntryFormId(entryForm.getEntryFormId());
            resultEntryForm.setMajorName(entryForm.getMajorName());
            User user = userMapper.queryUserNameByUsername(entryForm.getUserName());
            resultEntryForm.setUserName(user.getName());
            Minor minor = minorMapper.queryMinorName(entryForm.getMinorId());
            resultEntryForm.setMinorName(minor.getName());
            entryFormList.add(resultEntryForm);
        }
        return entryFormList;
    }

    private List<ResultApplyUser> convertResultApplyUserList(List<EntryForm> list){
        List<ResultApplyUser> userList = new ArrayList<>();
        for (EntryForm entryForm : list) {
            ResultApplyUser resultApplyUser = new ResultApplyUser();
            User user = userMapper.queryUserNameByUsername(entryForm.getUserName());
            resultApplyUser.setName(user.getName());
            resultApplyUser.setMajorName(entryForm.getMajorName());
            resultApplyUser.setInterestCourse(entryForm.getInterestCourse());
            resultApplyUser.setChecked(entryForm.getChecked());
            resultApplyUser.setAverageScore(entryForm.getAverageScore());
            Major major = majorMapper.queryMajorByName(entryForm.getMajorName());
            resultApplyUser.setMajorCourse(major.getMajorCourse());
            resultApplyUser.setCardId(entryForm.getCardId());
            Minor minor = minorMapper.queryMinorName(entryForm.getMinorId());
            resultApplyUser.setMinorName(minor.getName());
            userList.add(resultApplyUser);
        }
        return userList;
    }
}
