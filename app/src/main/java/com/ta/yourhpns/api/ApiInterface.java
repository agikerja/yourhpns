package com.ta.yourhpns.api;

import com.ta.yourhpns.model.BahanBakuModel;
import com.ta.yourhpns.model.BarangModel;
import com.ta.yourhpns.model.BuketAdminModel;
import com.ta.yourhpns.model.BuketModel;
import com.ta.yourhpns.model.DetailBahanBakuModel;
import com.ta.yourhpns.model.ListBahanBakuModel;
import com.ta.yourhpns.model.ListBarangModel;
import com.ta.yourhpns.model.ListBuketAdminModel;
import com.ta.yourhpns.model.ListDetailBahanbakuModel;
import com.ta.yourhpns.model.ListUserProfile;
import com.ta.yourhpns.model.ListUserProfileModel;
import com.ta.yourhpns.model.LoginModel;
import com.ta.yourhpns.model.RegisterModel;
import com.ta.yourhpns.model.StatusModel;
import com.ta.yourhpns.model.UserProfileModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("users/signin")
    @FormUrlEncoded
    Call<LoginModel> login(@Field("username") String username,
                           @Field("password") String password);

    @POST("users/signup")
    @FormUrlEncoded
    Call<RegisterModel> register(@Field("nama") String nama,
                                 @Field("username") String username,
                                 @Field("email")String email,
                                 @Field("password") String password);

    @POST("barang/insertBarang")
    @FormUrlEncoded
    Call<BarangModel> simpanBarangBaru(@Field("nama_barang") String nama_barang,
                                       @Field("jenis_barang") String jenis_barang,
                                       @Field("harga_barang") String harga_barang,
                                       @Field("stok_barang") String stok_barang);

    @POST("barang/updateBarang")
    @FormUrlEncoded
    Call<BarangModel> updateBarang(@Field ("id_barang")String id_barang,
                                   @Field ("nama_barang")String nama_barang,
                                   @Field ("jenis_barang")String jenis_barang,
                                   @Field ("harga_barang")String harga_barang,
                                   @Field ("stok_barang")String stok_barang);

    @GET("barang/showBarang")
    Call<ListBarangModel> getListBarang();

    @POST("barang/deleteBarang")
    @FormUrlEncoded
    Call<BarangModel> deleteBarang(@Field ("id_barang")String id_barang);

    @POST("barang/insertBarang")
    @FormUrlEncoded
    Call<BuketAdminModel> simpanBahanbaku(@Field("nama_bahanbaku") String nama_bahanbaku);

    @POST("bahanbaku/insertBahanBaku")
    @FormUrlEncoded
    Call<BahanBakuModel> simpanBahanBakuBaru(@Field("nama_bahanbaku") String nama_bahanbaku,
                                             @Field("jenis_bahanbaku") String jenis_bahanbaku,
                                             @Field("harga_bahanbaku") String harga_bahanbaku);

    @GET("bahanbaku/showBahanBaku")
    Call<ListBahanBakuModel> getListBahanBaku();

    @POST("bahanbaku/deleteBahanBaku")
    @FormUrlEncoded
    Call<BahanBakuModel> deleteBahanBaku(@Field ("id_bahanbaku")String id_bahanbaku);

    @POST("bahanbaku/updateBahanBaku")
    @FormUrlEncoded
    Call<BahanBakuModel> updateBahanBaku(@Field ("id_bahanbaku")String id_bahanbaku,
                                         @Field ("nama_bahanbaku")String nama_bahanbaku,
                                         @Field ("jenis_bahanbaku")String jenis_bahanbaku,
                                         @Field ("harga_bahanbaku")String harga_bahanbaku);

    @POST("buket/insertBuket")
    @FormUrlEncoded
    Call<BuketAdminModel> simpanBuket(@Field ("nama_buket") String nama_buket,
                                      @Field ("jenis_buket") String jenis_buket,
                                      @Field ("harga_buket") String harga_buket,
                                      @Field ("kode_buket") String kode_buket);

    @POST("detailbahanbaku/insertbahanbaku")
    @FormUrlEncoded
    Call<DetailBahanBakuModel> simpanDetailBahanBaku(@Field("nama_detailbahanbaku") String nama_detailbahanbaku,
                                                     @Field("jumlah_detailbahanbaku") String jumlah_detailbahanbaku,
                                                     @Field("harga_detailbahanbaku") String harga_detailbahanbaku,
                                                     @Field("kode_buket") String kode_buket);

    @POST("users/showUser")
    @FormUrlEncoded
    Call<ListUserProfileModel> showUserProfile(@Field("id")String id);

    @POST("users/updateUserProfile")
    @FormUrlEncoded
    Call<UserProfileModel> updateUserProfile(@Field("id")String id,
                                             @Field("nama")String nama,
                                             @Field("username")String username,
                                             @Field("email")String email);
    @POST("users/signuppegawai")
    @FormUrlEncoded
    Call<UserProfileModel> daftarPegawai(@Field("nama")String nama,
                                         @Field("username")String username,
                                         @Field("email")String email,
                                         @Field("password")String password,
                                         @Field("role")String role);

    @GET("users/showUserSuperAdmin")
    Call<ListUserProfileModel> getListUserProfile();

    @GET("buket/showBuket")
    Call<ListBuketAdminModel> getListBuketAdmin();

    @POST("buket/deleteBuket")
    @FormUrlEncoded
    Call<BuketAdminModel> deleteBuket(@Field ("id_buket")String id_buket);

    @POST("detailbahanbaku/deletedetailbahanbaku")
    @FormUrlEncoded
    Call<DetailBahanBakuModel> deleteDetail(@Field ("kode_buket")String kode_buket);

    @POST("detailbahanbaku/showBahanByKode")
    @FormUrlEncoded
    Call<ListDetailBahanbakuModel> showBahanBuket(@Field("kode_buket")String kode_buket);

    @POST("bahanbaku/showHargaByNama")
    @FormUrlEncoded
    Call<ListBuketAdminModel> showHarga(@Field("nama_bahanbaku")String nama_bahanbaku);

    @POST("buket/updatebuketadmin")
    @FormUrlEncoded
    Call<BuketAdminModel> updateBuket(@Field("id_buket")String id_buket,
                                      @Field("nama_buket")String nama_buket,
                                      @Field("jenis_buket")String jenis_buket,
                                      @Field("harga_buket")String harga_buket);

    @POST("detailbahanbaku/updatedetailbahanbaku")
    @FormUrlEncoded
    Call<DetailBahanBakuModel> updateDetail(@Field("id_detailbahanbaku") String id_detailbahanbaku,
                                            @Field("nama_detailbahanbaku") String nama_detailbahanbaku,
                                            @Field("jumlah_detailbahanbaku") String jumlah_detailbahanbaku,
                                            @Field("harga_detailbahanbaku") String harga_detailbahanbaku);

    @POST("detailbahanbaku/showidbykode")
    @FormUrlEncoded
    Call<StatusModel> getId(@Field("id_detailbahanbaku") String id_detailbahanbaku,
                            @Field("nama_detailbahanbaku") String nama_detailbahanbaku,
                            @Field("kode_buket") String kode_buket);

}
