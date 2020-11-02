package com.huc.service;

import com.huc.bean.EntryForm;
import com.huc.common.response.RespData;
import org.springframework.stereotype.Service;

@Service
public interface IEntryFormService {
    RespData apply(EntryForm entryForm, String name, String name1, String username);

    RespData check(Integer id);

    RespData queryToAudit();

    RespData checkApply(int entryFormId);

    RespData queryApplyUser();

    RespData allApply();
}
