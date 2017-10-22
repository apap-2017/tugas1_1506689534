package com.example.service;

import java.util.List;

import com.example.model.KelurahanModel;

public interface KelurahanService
{
   
   List<KelurahanModel> selectAllKelurahan();
   List<KelurahanModel> selectKelurahanByKecamatan(String id_kecamatan);
   KelurahanModel selectKelurahan (String id_kelurahan);
   KelurahanModel selectKelurahanByNama (String nama_kelurahan);
}

