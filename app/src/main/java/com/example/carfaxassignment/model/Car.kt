package com.example.carfaxassignment.model

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "car_table")
data class Car(
    @PrimaryKey
    @ColumnInfo
    val id: String,

    @ColumnInfo
    @Nullable
    val currentPrice: String,

    @ColumnInfo
    @Nullable
    val make: String,

    @ColumnInfo
    @Nullable
    val mileage: String,

    @ColumnInfo
    @Nullable
    val model: String,

    @ColumnInfo
    @Nullable
    val trim: String,

    @ColumnInfo
    @Nullable
    val year: String,

    @ColumnInfo
    @Nullable
    val images: image? = null,

    @ColumnInfo
    @Nullable
    val interiorColor: String,

    @ColumnInfo
    @Nullable
    val exteriorColor: String,

    @ColumnInfo
    @Nullable
    val bodytype: String,

    @ColumnInfo
    @Nullable
    val drivetype: String,

    @ColumnInfo
    @Nullable
    val engine: String,

    @ColumnInfo
    @Nullable
    val fuel: String,

    @ColumnInfo
    @Nullable
    val transmission: String,

    @ColumnInfo
    @Nullable
    val dealer: dealer? = null
): Serializable

data class Carlist(
    var listings: List<Car>
):Serializable

data class firstphoto(
    val medium: String
):Serializable

data class image(
    val firstPhoto: firstphoto
): Serializable

data class dealer(
    val city: String,
    val state: String,
    val phone: String

): Serializable