package com.huc.service.impl;

import com.huc.bean.BackupCopy;
import com.huc.bean.User;
import com.huc.dao.BackupCopyMapper;
import com.huc.dao.UserMapper;
import com.huc.service.IBackupCopyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BackupCopyServiceImpl implements IBackupCopyService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private BackupCopyMapper backupCopyMapper;

    @Override
    public void insertMessage(int userId) {
        User deleteUser = userMapper.updateUserSelectById(userId);
        BackupCopy backupCopy = new BackupCopy();
        backupCopy.setUserId(deleteUser.getUserId());
        backupCopy.setUsername(deleteUser.getUsername());
        backupCopy.setPassword(deleteUser.getPassword());
        backupCopy.setName(deleteUser.getName());
        backupCopy.setAge(deleteUser.getAge());
        backupCopy.setSex(deleteUser.getSex());
        backupCopy.setPhones(deleteUser.getPhones());
        backupCopy.setEmail(deleteUser.getEmail());
        backupCopy.setIdentity(deleteUser.getIdentity());
        backupCopyMapper.insert(backupCopy);
    }
}
