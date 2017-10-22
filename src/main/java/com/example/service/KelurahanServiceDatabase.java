package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.KelurahanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KelurahanServiceDatabase implements KelurahanService
{
	@Autowired
    private PendudukMapper pendudukMapper;

	@Override
	public KelurahanModel selectKelurahan(String id_kelurahan) {
		log.info("select kelurahan with id_kelurahan {}", id_kelurahan);
		return pendudukMapper.selectKelurahan(id_kelurahan);
	}

	@Override
	public List<KelurahanModel> selectAllKelurahan() {
		return pendudukMapper.selectAllKelurahan();
	}

	@Override
	public List<KelurahanModel> selectKelurahanByKecamatan(String id_kecamatan) {
		return pendudukMapper.selectKelurahanByKecamatan(id_kecamatan);
	}

	@Override
	public KelurahanModel selectKelurahanByNama(String nama_kelurahan) {
		return pendudukMapper.selectKelurahanByNama(nama_kelurahan);
	}

}
