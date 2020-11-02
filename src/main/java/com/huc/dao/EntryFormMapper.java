package com.huc.dao;

import com.huc.bean.EntryForm;

import java.util.List;

public interface EntryFormMapper {
    int insert(EntryForm record);

    int insertSelective(EntryForm record);

    List<EntryForm> queryToAudit(int checked);

    void checkApply(int entryFormId);

    List<EntryForm> queryApplyUser();

    void allApply();

    Integer queryEntryForm(String academyName,int minorId);

    Integer queryEntryFormByMajorNameAndMinorId(String majorName,int minorId);

    EntryForm queryMinorId(int entryFormId);

    EntryForm queryEntryFormByCondition(String userName,String majorName,int minorId);

    EntryForm queryEntryFormCardId(String cardId);

    EntryForm queryEntryFormByUserName(String userName);

    void updateEntryFormReserved1(String newFileName,String userName);
}