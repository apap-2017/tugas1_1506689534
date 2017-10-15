package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;


@Mapper
public interface PendudukMapper
{
	@Select("select * from penduduk where nik = #{nik}")
	PendudukModel selectPenduduk (@Param("nik") String nik);
	
	@Select("select * from penduduk where id_keluarga = #{id}")
	List<PendudukModel> selectAnggotaKeluarga (@Param("id") String id);
	
	@Select("select * from keluarga where nomor_kk = #{nomor_kk}")
	KeluargaModel selectKeluargaNKK (@Param("nomor_kk") String nomor_kk );
	
	@Select("select * from keluarga where id = #{id_keluarga}")
	KeluargaModel selectKeluarga (@Param("id_keluarga") String id_keluarga);
	
	@Select("select id, nomor_kk from keluarga where id = #{id_keluarga} and nomor_kk = #{nomor_kk}")
	List<KeluargaModel> selectAllKeluarga ();
	
	@Select("select * from kecamatan where id = #{id_kecamatan}")
	KecamatanModel selectKecamatan (@Param("id_kecamatan") String id_kecamatan);
	
	@Select("select * from kelurahan where id = #{id_kelurahan}")
	KelurahanModel selectKelurahan (@Param("id_kelurahan") String id_kelurahan);
	
	@Select("select * from kota where id = #{id_kota}")
	KotaModel selectKota (@Param("id_kota") String id_kota);

}





