package com.example.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel
{
    private String id;
    private String nomor_kk;
    private String alamat;
    private int RT;
    private int RW;
    private String id_kelurahan;
    private String is_tidak_berlaku;
  
}
