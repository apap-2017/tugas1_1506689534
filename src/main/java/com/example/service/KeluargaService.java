package com.example.service;

import java.util.List;

import com.example.model.KeluargaModel;

public interface KeluargaService
{
   KeluargaModel selectKeluarga (String id_keluarga);
   KeluargaModel selectKeluargaNKK(String nomor_kk);
}
