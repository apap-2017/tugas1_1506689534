package com.example.service;

import java.util.List;

import com.example.model.PendudukModel;
import com.example.model.KeluargaModel;

public interface PendudukService
{
    PendudukModel selectPenduduk (String nik);
    
    List<PendudukModel> selectAnggotaKeluarga(String id);
   
}
