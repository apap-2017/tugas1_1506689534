package com.example.service;

import java.util.List;

import com.example.model.KecamatanModel;

public interface KecamatanService
{
   KecamatanModel selectKecamatan (String id_kecamatan);
   KecamatanModel selectKecamatanByNama (String nama_kecamtan);
   List<KecamatanModel> selectAllKecamatan();
   List<KecamatanModel> selectKecamatanByKota(String id_kota);
}

