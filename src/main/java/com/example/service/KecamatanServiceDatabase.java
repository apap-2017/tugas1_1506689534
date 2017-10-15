package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.KecamatanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KecamatanServiceDatabase implements KecamatanService
{
	@Autowired
    private PendudukMapper pendudukMapper;

	@Override
	public KecamatanModel selectKecamatan(String id_kecamatan) {
		log.info ("select student with id_kecamatan {}", id_kecamatan);
        return pendudukMapper.selectKecamatan(id_kecamatan);
	}


}
