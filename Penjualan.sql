-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 12 Des 2019 pada 11.36
-- Versi server: 10.1.36-MariaDB
-- Versi PHP: 7.0.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Penjualan`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `BARANG`
--

CREATE TABLE `BARANG` (
  `barcode` char(16) NOT NULL,
  `nama_barang` varchar(35) NOT NULL,
  `harga_jual` double NOT NULL,
  `harga_beli` double NOT NULL,
  `netto` int(11) NOT NULL,
  `kd_kategori` char(4) NOT NULL,
  `stok` int(11) NOT NULL,
  `diskon` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `BARANG`
--

INSERT INTO `BARANG` (`barcode`, `nama_barang`, `harga_jual`, `harga_beli`, `netto`, `kd_kategori`, `stok`, `diskon`) VALUES
('653314504513', 'KUSUKA SINGKONG RUMPUT LAUT', 7500, 5600, 60, 'K092', 497, 0),
('8888166991125', 'MONDE BUTTER COOKIES', 103000, 90000, 150, 'K092', 500, 0),
('8991001302267', 'CERES RICE CHOCOLATE', 10000, 8900, 90, 'K092', 493, 0),
('8991906101026', 'DJARUM SUPER FILTER', 16500, 15000, 16, 'K099', 500, 0),
('8991906101255', 'DJARUM BLACK MENTOL', 21000, 18000, 18, 'K099', 497, 0),
('8992222053426', 'GATSBY URBAN COLOGNE', 17000, 16500, 125, 'K095', 500, 0),
('8992304010293', 'GARNIER LIGHT COMPLETE WHITENING', 26700, 25300, 145, 'K100', 500, 0),
('8992304015298', 'GARNIER MEN ACNO FIGHT FOAM', 29500, 27600, 130, 'K100', 499, 0),
('8992628032155', 'BIMOLI SPECIAL POUNCH', 22000, 20000, 2000, 'K097', 500, 0),
('8992761122324', 'FRESTEA MELATI BTL', 5300, 4500, 500, 'K093', 500, 0),
('8992761136055', 'FANTA SOFT DRINK STRAWBERRY PET', 12500, 11800, 1500, 'K093', 500, 0),
('8992761147129', 'FANTA GRAPE', 4500, 3400, 425, 'K093', 497, 0),
('8992832601444', 'CASABLANCA HITAM KLG', 34200, 32000, 1000, 'K095', 496, 0),
('8992832601499', 'CASABLANCA MERAH KLG', 34200, 32000, 100, 'K095', 499, 0),
('8996001302323', 'ROMA MALKIST ABON', 7500, 5600, 75, 'K092', 500, 0),
('8996001355756', 'BENG-BENG MAXX', 4500, 3200, 32, 'K092', 495, 0),
('8996196000356', 'PIATTOS SP PANGGANG', 4700, 3500, 12, 'K092', 500, 0),
('8998181941132', 'DUNHILL FINE CUT FILTER', 16500, 14500, 16, 'K099', 499, 0),
('8998866107143', 'POSH MEN COOL BLUE', 35000, 30000, 150, 'K095', 498, 0),
('8998866181075', 'CIPTADENT FRESH MINT', 8000, 7500, 120, 'K094', 500, 0),
('8998866500708', 'FLORIDINA JUICE PULP ORANGE BTL', 3000, 2400, 360, 'K093', 498, 0),
('8999999002503', 'BANGO KECAP MANIS BT', 21300, 20000, 275, 'K097', 500, 0),
('8999999023171', 'BUAVITA GRAPE TPK', 7500, 6400, 250, 'K093', 500, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `DETAIL_PEMBELIAN`
--

CREATE TABLE `DETAIL_PEMBELIAN` (
  `kd_pembelian` char(7) NOT NULL,
  `barcode` char(16) NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `sub_total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `DETAIL_PEMBELIAN_TEMP`
--

CREATE TABLE `DETAIL_PEMBELIAN_TEMP` (
  `kd_pembelian` char(7) NOT NULL,
  `barcode` char(16) NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `sub_total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `DETAIL_PENJUALAN`
--

CREATE TABLE `DETAIL_PENJUALAN` (
  `kd_penjualan` char(7) NOT NULL,
  `barcode` char(16) NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `sub_total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `DETAIL_PENJUALAN`
--

INSERT INTO `DETAIL_PENJUALAN` (`kd_penjualan`, `barcode`, `jumlah_barang`, `sub_total`) VALUES
('3240001', '8996001355756', 3, 13500),
('3240002', '653314504513', 3, 22500);

--
-- Trigger `DETAIL_PENJUALAN`
--
DELIMITER $$
CREATE TRIGGER `UpdateStok` AFTER INSERT ON `DETAIL_PENJUALAN` FOR EACH ROW UPDATE BARANG SET stok = (stok - NEW.jumlah_barang) WHERE barcode = new.barcode
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `DETAIL_PENJUALAN_TEMP`
--

CREATE TABLE `DETAIL_PENJUALAN_TEMP` (
  `kd_penjualan` char(7) NOT NULL,
  `barcode` char(16) NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `sub_total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `KARYAWAN`
--

CREATE TABLE `KARYAWAN` (
  `kd_karyawan` char(8) NOT NULL,
  `nama_karyawan` varchar(35) NOT NULL,
  `jenis_kelamin` enum('Pria','Wanita') NOT NULL,
  `telp_karyawan` char(13) NOT NULL,
  `alamat_karyawan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `KARYAWAN`
--

INSERT INTO `KARYAWAN` (`kd_karyawan`, `nama_karyawan`, `jenis_kelamin`, `telp_karyawan`, `alamat_karyawan`) VALUES
('USR07292', 'Manji Sumanji', 'Pria', '082938277392', 'Jl. Soekarno Hatta, No. 32, Lowokwaru, Malang'),
('USR07293', 'Kurniat', 'Wanita', '085662736231', 'Jl. Pahlawan, Desa Selogudig Kulon, Pajarakan, Proboliinggo'),
('USR07294', 'Samsulah', 'Pria', '089773823723', 'Jl. Teluk Bayur, Surabaya'),
('USR07295', 'Arifin', 'Pria', '087337436343', 'Jl. Pandean Kraksaan	'),
('USR07296', 'Nina', 'Wanita', '085736438273', 'Jl. Tanah Abang, No. 40, Tebet, Jakarta Utara'),
('USR07297', 'Namira', 'Wanita', '085435226327', 'Jl. Kusumo, No. 29, Surabaya'),
('USR07298', 'Putri Afina', 'Wanita', '086773627326', 'Jl. Teluk Mandar, Arjosari, Malang'),
('USR07299', 'Laila Fitria', 'Wanita', '085662536235', 'Jl. Merauke, No.32, Malang'),
('USR07300', 'Zainal Abidin', 'Pria', '087336728372', 'Jl. Cokroaminoto, Probolinggo'),
('USR07301', 'Syafrizal', 'Pria', '089772637263', 'Jl. Panjaitan, No. 25, Malang'),
('USR07302', 'Sijono', 'Pria', '089726372362', 'Jl. Lebak Bulus, Jakarta');

-- --------------------------------------------------------

--
-- Struktur dari tabel `KATEGORI_BARANG`
--

CREATE TABLE `KATEGORI_BARANG` (
  `kd_kategori` char(4) NOT NULL,
  `nama_kategori` varchar(35) NOT NULL,
  `jenis_satuan` enum('ML','GR','BIJI') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `KATEGORI_BARANG`
--

INSERT INTO `KATEGORI_BARANG` (`kd_kategori`, `nama_kategori`, `jenis_satuan`) VALUES
('K092', 'Makanan', 'GR'),
('K093', 'Minuman', 'ML'),
('K094', 'Sabun Mandi', 'GR'),
('K095', 'Parfume', 'ML'),
('K096', 'Deterjen', 'GR'),
('K097', 'Saos Kecap', 'ML'),
('K098', 'Minyak Goreng', 'ML'),
('K099', 'Rokok', 'BIJI'),
('K100', 'Sabun Muka', 'ML'),
('K101', 'Alat Pembersih', 'BIJI');

-- --------------------------------------------------------

--
-- Struktur dari tabel `PEMBELIAN`
--

CREATE TABLE `PEMBELIAN` (
  `kd_pembelian` char(7) NOT NULL,
  `kd_supplier` char(4) NOT NULL,
  `kd_karyawan` char(8) NOT NULL,
  `tgl_pembelian` date NOT NULL,
  `total_pembelian` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `kembali` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `PENJUALAN`
--

CREATE TABLE `PENJUALAN` (
  `kd_penjualan` char(7) NOT NULL,
  `kd_karyawan` char(8) NOT NULL,
  `tgl_penjualan` date NOT NULL,
  `total_penjualan` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `kembali` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `PENJUALAN`
--

INSERT INTO `PENJUALAN` (`kd_penjualan`, `kd_karyawan`, `tgl_penjualan`, `total_penjualan`, `bayar`, `kembali`) VALUES
('3240001', 'USR07293', '2019-12-11', 13500, 15000, 1500),
('3240002', 'USR07302', '2019-12-11', 22500, 25000, 2500);

-- --------------------------------------------------------

--
-- Struktur dari tabel `SUPPLIER`
--

CREATE TABLE `SUPPLIER` (
  `kd_supplier` char(4) NOT NULL,
  `nama_supplier` varchar(35) NOT NULL,
  `jenis_kelamin` enum('Pria','Wanita') NOT NULL,
  `telp_supplier` char(13) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `SUPPLIER`
--

INSERT INTO `SUPPLIER` (`kd_supplier`, `nama_supplier`, `jenis_kelamin`, `telp_supplier`, `alamat`) VALUES
('S210', 'Sihono', 'Pria', '082664536354', 'Jl. Sumedang, No. 31, Sumedang, Sumatera Utara'),
('S211', 'John Armayndo', 'Pria', '082333672536', 'Jl. Tidar Sakti, Malang'),
('S212', 'Alvin Rian', 'Pria', '085663536473', 'Jl. Candi Atas, Malang'),
('S213', 'Budi Prayogi', 'Pria', '089773647364', 'Jl. Candi 3A, Malang'),
('S214', 'Dinda Ayuni', 'Wanita', '089772837283', 'Jl. Bermi, Probolinggo'),
('S215', 'Khoirudin', 'Pria', '086773647364', 'Jl Pahlawan, Probolinggo'),
('S218', 'Wulandari', 'Wanita', '085772637362', 'Jl. Alasumur, Kraksaan, Probolinggo');

-- --------------------------------------------------------

--
-- Struktur dari tabel `USER`
--

CREATE TABLE `USER` (
  `kd_user` char(8) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `jabatan` enum('Kasir','Pramuniaga','Admin') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `USER`
--

INSERT INTO `USER` (`kd_user`, `username`, `password`, `jabatan`) VALUES
('ADM07291', 'Admin', 'Admin', 'Kasir'),
('USR07292', 'Manji', 'Manji', 'Kasir'),
('USR07293', 'Kurniat', 'Kurniat', 'Kasir'),
('USR07294', 'Mpik', 'Mpik', 'Kasir'),
('USR07295', 'Afin', 'Afin', 'Kasir'),
('USR07296', 'Ina', 'Ina', 'Kasir'),
('USR07297', 'Mira', 'Mira', 'Pramuniaga'),
('USR07298', 'Fina', 'Fina', 'Kasir'),
('USR07299', 'Fitri', 'Fitri', 'Kasir'),
('USR07300', 'Bidin', 'Bidin', 'Pramuniaga'),
('USR07301', 'Rizal', 'as', 'Pramuniaga'),
('USR07302', 'a', 'a', 'Kasir');

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `VIEW_BARANG`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `VIEW_BARANG` (
`barcode` char(16)
,`nama_barang` varchar(35)
,`harga_jual` double
,`harga_beli` double
,`netto` varchar(16)
,`nama_kategori` varchar(35)
,`stok` int(11)
,`diskon` varchar(12)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `VIEW_DETAIL_PENJUALAN`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `VIEW_DETAIL_PENJUALAN` (
`kd_penjualan` char(7)
,`barcode` char(16)
,`nama_barang` varchar(35)
,`jumlah_barang` int(11)
,`harga_jual` double
,`sub_total` int(11)
,`diskon` int(11)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `VIEW_DETAIL_PENJUALAN_TEMP`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `VIEW_DETAIL_PENJUALAN_TEMP` (
`barcode` char(16)
,`nama_barang` varchar(35)
,`jumlah_barang` int(11)
,`harga_jual` double
,`sub_total` int(11)
,`diskon` int(11)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `VIEW_KARYAWAN`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `VIEW_KARYAWAN` (
`kd_karyawan` char(8)
,`nama_karyawan` varchar(35)
,`jenis_kelamin` enum('Pria','Wanita')
,`telp_karyawan` char(13)
,`alamat_karyawan` text
,`username` varchar(25)
,`password` varchar(25)
,`jabatan` enum('Kasir','Pramuniaga','Admin')
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `VIEW_PENJUALAN_KARYAWAN`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `VIEW_PENJUALAN_KARYAWAN` (
`kd_penjualan` char(7)
,`nama_karyawan` varchar(35)
,`tgl_penjualan` date
,`total_penjualan` int(11)
,`bayar` int(11)
,`kembali` int(11)
);

-- --------------------------------------------------------

--
-- Struktur untuk view `VIEW_BARANG`
--
DROP TABLE IF EXISTS `VIEW_BARANG`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `VIEW_BARANG`  AS  select `BARANG`.`barcode` AS `barcode`,`BARANG`.`nama_barang` AS `nama_barang`,`BARANG`.`harga_jual` AS `harga_jual`,`BARANG`.`harga_beli` AS `harga_beli`,concat(`BARANG`.`netto`,' ',`KATEGORI_BARANG`.`jenis_satuan`) AS `netto`,`KATEGORI_BARANG`.`nama_kategori` AS `nama_kategori`,`BARANG`.`stok` AS `stok`,concat(`BARANG`.`diskon`,'%') AS `diskon` from (`BARANG` join `KATEGORI_BARANG`) where (`BARANG`.`kd_kategori` = `KATEGORI_BARANG`.`kd_kategori`) ;

-- --------------------------------------------------------

--
-- Struktur untuk view `VIEW_DETAIL_PENJUALAN`
--
DROP TABLE IF EXISTS `VIEW_DETAIL_PENJUALAN`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `VIEW_DETAIL_PENJUALAN`  AS  select `DETAIL_PENJUALAN`.`kd_penjualan` AS `kd_penjualan`,`BARANG`.`barcode` AS `barcode`,`BARANG`.`nama_barang` AS `nama_barang`,`DETAIL_PENJUALAN`.`jumlah_barang` AS `jumlah_barang`,`BARANG`.`harga_jual` AS `harga_jual`,`DETAIL_PENJUALAN`.`sub_total` AS `sub_total`,`BARANG`.`diskon` AS `diskon` from (`BARANG` join `DETAIL_PENJUALAN`) where (`BARANG`.`barcode` = `DETAIL_PENJUALAN`.`barcode`) ;

-- --------------------------------------------------------

--
-- Struktur untuk view `VIEW_DETAIL_PENJUALAN_TEMP`
--
DROP TABLE IF EXISTS `VIEW_DETAIL_PENJUALAN_TEMP`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `VIEW_DETAIL_PENJUALAN_TEMP`  AS  select `BARANG`.`barcode` AS `barcode`,`BARANG`.`nama_barang` AS `nama_barang`,`DETAIL_PENJUALAN_TEMP`.`jumlah_barang` AS `jumlah_barang`,`BARANG`.`harga_jual` AS `harga_jual`,`DETAIL_PENJUALAN_TEMP`.`sub_total` AS `sub_total`,`BARANG`.`diskon` AS `diskon` from (`BARANG` join `DETAIL_PENJUALAN_TEMP`) where (`BARANG`.`barcode` = `DETAIL_PENJUALAN_TEMP`.`barcode`) ;

-- --------------------------------------------------------

--
-- Struktur untuk view `VIEW_KARYAWAN`
--
DROP TABLE IF EXISTS `VIEW_KARYAWAN`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `VIEW_KARYAWAN`  AS  select `KARYAWAN`.`kd_karyawan` AS `kd_karyawan`,`KARYAWAN`.`nama_karyawan` AS `nama_karyawan`,`KARYAWAN`.`jenis_kelamin` AS `jenis_kelamin`,`KARYAWAN`.`telp_karyawan` AS `telp_karyawan`,`KARYAWAN`.`alamat_karyawan` AS `alamat_karyawan`,`USER`.`username` AS `username`,`USER`.`password` AS `password`,`USER`.`jabatan` AS `jabatan` from (`USER` join `KARYAWAN`) where ((`KARYAWAN`.`kd_karyawan` = `USER`.`kd_user`) and (`USER`.`jabatan` <> 'Admin')) ;

-- --------------------------------------------------------

--
-- Struktur untuk view `VIEW_PENJUALAN_KARYAWAN`
--
DROP TABLE IF EXISTS `VIEW_PENJUALAN_KARYAWAN`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `VIEW_PENJUALAN_KARYAWAN`  AS  select `PENJUALAN`.`kd_penjualan` AS `kd_penjualan`,`KARYAWAN`.`nama_karyawan` AS `nama_karyawan`,`PENJUALAN`.`tgl_penjualan` AS `tgl_penjualan`,`PENJUALAN`.`total_penjualan` AS `total_penjualan`,`PENJUALAN`.`bayar` AS `bayar`,`PENJUALAN`.`kembali` AS `kembali` from (`PENJUALAN` join `KARYAWAN`) where (`PENJUALAN`.`kd_karyawan` = `KARYAWAN`.`kd_karyawan`) ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `BARANG`
--
ALTER TABLE `BARANG`
  ADD PRIMARY KEY (`barcode`),
  ADD KEY `FK_KD_KATEGORI` (`kd_kategori`);

--
-- Indeks untuk tabel `DETAIL_PEMBELIAN`
--
ALTER TABLE `DETAIL_PEMBELIAN`
  ADD KEY `FK_KD_PEMBELIAN` (`kd_pembelian`),
  ADD KEY `FK_KD_BARANG2` (`barcode`);

--
-- Indeks untuk tabel `DETAIL_PENJUALAN`
--
ALTER TABLE `DETAIL_PENJUALAN`
  ADD KEY `FK_KD_PENJUALAN` (`kd_penjualan`),
  ADD KEY `FK_KD_BARANG1` (`barcode`);

--
-- Indeks untuk tabel `KARYAWAN`
--
ALTER TABLE `KARYAWAN`
  ADD PRIMARY KEY (`kd_karyawan`);

--
-- Indeks untuk tabel `KATEGORI_BARANG`
--
ALTER TABLE `KATEGORI_BARANG`
  ADD PRIMARY KEY (`kd_kategori`);

--
-- Indeks untuk tabel `PEMBELIAN`
--
ALTER TABLE `PEMBELIAN`
  ADD PRIMARY KEY (`kd_pembelian`),
  ADD KEY `FK_KD_SUPPLIER` (`kd_supplier`),
  ADD KEY `FK_KD_KARYAWAN1` (`kd_karyawan`);

--
-- Indeks untuk tabel `PENJUALAN`
--
ALTER TABLE `PENJUALAN`
  ADD PRIMARY KEY (`kd_penjualan`),
  ADD KEY `FK_KD_KARYAWAN2` (`kd_karyawan`);

--
-- Indeks untuk tabel `SUPPLIER`
--
ALTER TABLE `SUPPLIER`
  ADD PRIMARY KEY (`kd_supplier`);

--
-- Indeks untuk tabel `USER`
--
ALTER TABLE `USER`
  ADD PRIMARY KEY (`kd_user`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `BARANG`
--
ALTER TABLE `BARANG`
  ADD CONSTRAINT `FK_KD_KATEGORI` FOREIGN KEY (`kd_kategori`) REFERENCES `KATEGORI_BARANG` (`kd_kategori`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `DETAIL_PEMBELIAN`
--
ALTER TABLE `DETAIL_PEMBELIAN`
  ADD CONSTRAINT `FK_KD_BARANG2` FOREIGN KEY (`barcode`) REFERENCES `BARANG` (`barcode`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_KD_PEMBELIAN` FOREIGN KEY (`kd_pembelian`) REFERENCES `PEMBELIAN` (`kd_pembelian`);

--
-- Ketidakleluasaan untuk tabel `DETAIL_PENJUALAN`
--
ALTER TABLE `DETAIL_PENJUALAN`
  ADD CONSTRAINT `FK_KD_BARANG1` FOREIGN KEY (`barcode`) REFERENCES `BARANG` (`barcode`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_KD_PENJUALAN` FOREIGN KEY (`kd_penjualan`) REFERENCES `PENJUALAN` (`kd_penjualan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `KARYAWAN`
--
ALTER TABLE `KARYAWAN`
  ADD CONSTRAINT `FK_KD_USER` FOREIGN KEY (`kd_karyawan`) REFERENCES `USER` (`kd_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `PEMBELIAN`
--
ALTER TABLE `PEMBELIAN`
  ADD CONSTRAINT `FK_KD_KARYAWAN1` FOREIGN KEY (`kd_karyawan`) REFERENCES `USER` (`kd_user`),
  ADD CONSTRAINT `FK_KD_SUPPLIER` FOREIGN KEY (`kd_supplier`) REFERENCES `SUPPLIER` (`kd_supplier`);

--
-- Ketidakleluasaan untuk tabel `PENJUALAN`
--
ALTER TABLE `PENJUALAN`
  ADD CONSTRAINT `FK_KD_KARYAWAN2` FOREIGN KEY (`kd_karyawan`) REFERENCES `USER` (`kd_user`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
