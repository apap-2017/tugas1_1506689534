package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;


@Mapper
public interface PendudukMapper
{
	//PENDUDUK
	@Select("select * from penduduk")
	List<PendudukModel> selectAllPenduduk();
	
	@Select("select * from penduduk where nik = #{nik}")
	PendudukModel selectPenduduk (@Param("nik") String nik);
	
	@Select("select * from penduduk where id_keluarga = #{id}")
	List<PendudukModel> selectAnggotaKeluarga (@Param("id") String id);
	
	@Select("select * from penduduk where nik LIKE #{nik}")
	List<PendudukModel> selectNIK (@Param("nik") String nik);

	//TAMBAH PENDUDUK
	@Insert("insert into penduduk (id, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, golongan_darah, agama, status_perkawinan, pekerjaan, is_wni, is_wafat, id_keluarga, status_dalam_keluarga) "
			+ "VALUES (#{id}, #{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{golongan_darah}, #{agama}, #{status_perkawinan}, #{pekerjaan}, #{is_wni}, #{is_wafat}, #{id_keluarga}, #{status_dalam_keluarga})")
    void addPenduduk (PendudukModel penduduk);
	
	//UPDATE PENDUDUK
	@Update("update penduduk SET nik = #{nik}, nama =#{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}, jenis_kelamin = #{jenis_kelamin}, golongan_darah = #{golongan_darah}, agama = #{agama}, status_perkawinan = #{status_perkawinan}, pekerjaan = #{pekerjaan}, is_wni = #{is_wni}, is_wafat = #{is_wafat}, id_keluarga = #{id_keluarga}, status_dalam_keluarga = #{status_dalam_keluarga} WHERE id = #{id}")
    void updatePenduduk(PendudukModel penduduk);
	
	//KELUARGA
	@Select("select * from keluarga")
	List<KeluargaModel> selectAllKeluarga ();
	
	@Select("select * from keluarga where nomor_kk = #{nomor_kk}")
	KeluargaModel selectKeluargaNKK (@Param("nomor_kk") String nomor_kk );
	
	@Select("select * from keluarga where id = #{id_keluarga}")
	KeluargaModel selectKeluarga (@Param("id_keluarga") String id_keluarga);
	
	@Select("select * from keluarga where nomor_kk LIKE #{nomor_kk}")
	List<KeluargaModel> selectNKK (@Param("nomor_kk") String nomor_kk);

	//TAMBAH KELUARGA
	@Insert("insert into keluarga (id, nomor_kk, alamat, RT, RW, id_kelurahan, is_tidak_berlaku) "
	+ "values (#{id}, #{nomor_kk}, #{alamat}, #{RT}, #{RW}, #{id_kelurahan}, #{is_tidak_berlaku})")
	void addKeluarga(KeluargaModel keluarga);
	
	//UPDATE KELUARGA
	  final String UPDATE = "update keluarga set nomor_kk = #{nomor_kk}, alamat = #{alamat}, "
              + "RT = #{RT}, RW = #{RW}, id_kelurahan= #{id_kelurahan}, is_tidak_berlaku = #{is_tidak_berlaku} where id = #{id}";

	  @Update(UPDATE)
	  void update(KeluargaModel keluargaModel);

	//KOTA
	@Select("select * from kota")
	List<KotaModel> selectAllKota();
	
	@Select("select * from kota where id = #{id_kota}")
	KotaModel selectKota (@Param("id_kota") String id_kota);
	
	@Select("select * from kota where nama_kota = #{nama_kota}")
	KotaModel selectKotaByNama (@Param("nama_kota") String nama_kota);
	
	
	//KECAMATAN
	@Select("select * from kecamatan")
	List<KecamatanModel> selectAllKecamatan();
	
	@Select("select * from kecamatan where id = #{id_kecamatan}")
	KecamatanModel selectKecamatan (@Param("id_kecamatan") String id_kecamatan);
	
	@Select("select * from kecamatan where id_kota = #{id_kota}")
	List<KecamatanModel> selectKecamatanByKota(@Param("id_kota") String id_kota);
	
	@Select("select * from kecamatan where nama_kecamatan = #{nama_kecamatan}")
	KecamatanModel selectKecamatanByNama (@Param("nama_kecamatan") String nama_kecamatan);
	
	//KELURAHAN
	@Select("select * from kelurahan")
	List<KelurahanModel> selectAllKelurahan();
	
	@Select("select * from kelurahan where id = #{id_kelurahan}")
	KelurahanModel selectKelurahan (@Param("id_kelurahan") String id_kelurahan);
	
	@Select("select * from kelurahan where id_kecamatan = #{id_kecamatan}")
	List<KelurahanModel> selectKelurahanByKecamatan(@Param("id_kecamatan") String id_kecamatan);
	
	@Select("select * from kelurahan where nama_kelurahan = #{nama_kelurahan}")
	KelurahanModel selectKelurahanByNama (@Param("nama_kelurahan") String nama_kelurahan);
	
	//UPDATE KEMATIAN PENDUDUK
	final String UPDATE_IS_WAFAT = "update penduduk set is_wafat = #{is_wafat} where id = #{id} ";
	@Update(UPDATE_IS_WAFAT)
	void updateIsWafat(@Param("is_wafat") String is_wafat, @Param("id") String id);
	
	//UPDATE KEMATIAN KELUARGA
	final String UPDATE_IS_TIDAK_BERLAKU = "update keluarga set is_tidak_berlaku = #{is_tidak_berlaku} where id = #{id}";
    @Update(UPDATE_IS_TIDAK_BERLAKU)
    void updateIsTidakBerlaku(@Param("is_tidak_berlaku") String is_tidak_berlaku, @Param("id") String id);
    
    //CARI PENDUDUK
    @Select("SELECT * from penduduk where id_keluarga IN (select id from keluarga where id_kelurahan = #{id_kelurahan})")
	List<PendudukModel> selectPendudukByKelurahan(@Param("id_kelurahan") String id_kelurahan);
}
	





