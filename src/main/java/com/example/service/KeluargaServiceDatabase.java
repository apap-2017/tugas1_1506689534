package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.KeluargaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService
{
	@Autowired
    private PendudukMapper pendudukMapper;

	@Override
	public KeluargaModel selectKeluarga(String id_keluarga) {
		log.info("select keluarga with id_keluarga {}", id_keluarga);
		return pendudukMapper.selectKeluarga(id_keluarga);
	}

	@Override
	public KeluargaModel selectKeluargaNKK(String nomor_kk) {
		log.info("select keluarga with nomor_kk {}", nomor_kk);
		return pendudukMapper.selectKeluargaNKK(nomor_kk);
	}

}
