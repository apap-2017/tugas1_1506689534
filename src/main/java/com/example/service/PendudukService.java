package com.example.service;

import java.util.List;

import com.example.model.PendudukModel;
import com.example.model.KeluargaModel;

public interface PendudukService
{
   
    List<PendudukModel> selectAllPenduduk();
    List<PendudukModel> selectAnggotaKeluarga(String id);
    List<PendudukModel> selectNIK(String nik);
    PendudukModel selectPenduduk (String nik);
    List<PendudukModel> selectPendudukByKelurahan(String id_kelurahan);
    
    //TAMBAH PENDUDUK
    void addPenduduk(PendudukModel penduduk);
   
    //UPDATE PENDUDUK
    void updatePenduduk(PendudukModel penduduk);
    
    //UPDATE KEMATIAN PENDUDUK
    void updateIsWafat(String is_wafat, String id);
   
}
