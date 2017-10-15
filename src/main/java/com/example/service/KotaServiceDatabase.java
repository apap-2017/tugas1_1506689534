package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.KotaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KotaServiceDatabase implements KotaService
{
	@Autowired
    private PendudukMapper pendudukMapper;

	@Override
	public KotaModel selectKota(String id_kota) {
		log.info("select kota with id_kota {}", id_kota);
		return pendudukMapper.selectKota(id_kota);
	}

}
