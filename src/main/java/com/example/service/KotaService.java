package com.example.service;

import com.example.model.KotaModel;
import java.util.List;

public interface KotaService
{
	List<KotaModel> selectAllKota();
	KotaModel selectKota (String id_kota);
	KotaModel selectKotaByNama (String nama_kota);

}