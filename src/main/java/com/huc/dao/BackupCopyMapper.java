package com.huc.dao;

import com.huc.bean.BackupCopy;

public interface BackupCopyMapper {
    int insert(BackupCopy record);

    int insertSelective(BackupCopy record);
}