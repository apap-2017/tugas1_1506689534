package com.example.service;

import java.util.List;

import com.example.model.KeluargaModel;

public interface KeluargaService
{

	List<KeluargaModel> selectAllKeluarga();
	List<KeluargaModel> selectNKK(String nomor_kk);
    KeluargaModel selectKeluarga (String id_keluarga);
    KeluargaModel selectKeluargaNKK(String nomor_kk);
    
    //TAMBAH KELUARGA
    void addKeluarga(KeluargaModel keluarga);
    
    //UPDATE KELUARGA
    void update(KeluargaModel keluargaModel);
    
    //UPDATE KEMATIAN KELUARGA
    void updateIsTidakBerlaku(String is_tidak_berlaku, String id);
    
}
