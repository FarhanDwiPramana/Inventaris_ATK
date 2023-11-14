-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 17 Des 2022 pada 06.19
-- Versi server: 10.4.24-MariaDB
-- Versi PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uas`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `idbarang` int(11) NOT NULL,
  `namabarang` varchar(255) NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`idbarang`, `namabarang`, `qty`) VALUES
(132, 'Pensil', 11),
(133, 'Penggaris', 7),
(134, 'Penghapus', 10),
(135, 'Bolpoin', 4),
(136, 'Buku Gambar', 0),
(137, 'Buku tulis', 0),
(138, 'Spidol', 90),
(139, 'Pensil Warna', 0),
(140, 'Kertas Bufalo', 11),
(141, 'Kertas HVS', 20),
(142, 'Lem Glukol', 5),
(143, 'Kertas Manila', 32),
(144, 'Tinta Bolpoin', 12),
(145, 'Kapur Tulis', 10),
(146, 'Kapur Warna', 10),
(147, 'Name Card', 25),
(148, 'Tipe X', 23),
(149, 'Amplop', 30);

-- --------------------------------------------------------

--
-- Struktur dari tabel `brgkeluar`
--

CREATE TABLE `brgkeluar` (
  `idbk` int(11) NOT NULL,
  `idbarang` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `brgkeluar`
--

INSERT INTO `brgkeluar` (`idbk`, `idbarang`, `tanggal`, `qty`) VALUES
(6, 132, '2022-12-15', 5),
(7, 132, '2022-12-16', 2),
(8, 135, '2022-12-16', 3),
(9, 138, '2022-12-20', 10),
(10, 146, '2022-12-05', 10),
(11, 145, '2022-12-09', 10);

-- --------------------------------------------------------

--
-- Struktur dari tabel `brgmasuk`
--

CREATE TABLE `brgmasuk` (
  `idbm` int(11) NOT NULL,
  `idbarang` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `brgmasuk`
--

INSERT INTO `brgmasuk` (`idbm`, `idbarang`, `tanggal`, `qty`) VALUES
(12, 132, '2022-12-15', 10),
(13, 135, '2022-12-16', 4),
(14, 138, '2022-12-01', 100),
(15, 140, '2022-12-05', 20),
(16, 141, '2022-12-10', 10),
(17, 143, '2022-12-17', 12),
(18, 146, '2022-12-02', 20);

-- --------------------------------------------------------

--
-- Struktur dari tabel `brgrusak`
--

CREATE TABLE `brgrusak` (
  `idbr` int(11) NOT NULL,
  `idbarang` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `qty` int(11) NOT NULL,
  `ket` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `brgrusak`
--

INSERT INTO `brgrusak` (`idbr`, `idbarang`, `tanggal`, `qty`, `ket`) VALUES
(4, 135, '2022-12-16', 8, 'Cacat'),
(5, 132, '2022-12-22', 12, 'Rusak'),
(6, 133, '2022-12-12', 5, 'Cacat'),
(7, 140, '2022-12-12', 10, 'Cacat'),
(8, 140, '2022-12-18', 11, 'Cacat');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`idbarang`);

--
-- Indeks untuk tabel `brgkeluar`
--
ALTER TABLE `brgkeluar`
  ADD PRIMARY KEY (`idbk`),
  ADD KEY `idbarang` (`idbarang`);

--
-- Indeks untuk tabel `brgmasuk`
--
ALTER TABLE `brgmasuk`
  ADD PRIMARY KEY (`idbm`),
  ADD KEY `idbarang` (`idbarang`);

--
-- Indeks untuk tabel `brgrusak`
--
ALTER TABLE `brgrusak`
  ADD PRIMARY KEY (`idbr`),
  ADD KEY `idbarang` (`idbarang`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `barang`
--
ALTER TABLE `barang`
  MODIFY `idbarang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=150;

--
-- AUTO_INCREMENT untuk tabel `brgkeluar`
--
ALTER TABLE `brgkeluar`
  MODIFY `idbk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT untuk tabel `brgmasuk`
--
ALTER TABLE `brgmasuk`
  MODIFY `idbm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT untuk tabel `brgrusak`
--
ALTER TABLE `brgrusak`
  MODIFY `idbr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `brgkeluar`
--
ALTER TABLE `brgkeluar`
  ADD CONSTRAINT `brgkeluar_ibfk_1` FOREIGN KEY (`idbarang`) REFERENCES `barang` (`idbarang`);

--
-- Ketidakleluasaan untuk tabel `brgmasuk`
--
ALTER TABLE `brgmasuk`
  ADD CONSTRAINT `brgmasuk_ibfk_1` FOREIGN KEY (`idbarang`) REFERENCES `barang` (`idbarang`);

--
-- Ketidakleluasaan untuk tabel `brgrusak`
--
ALTER TABLE `brgrusak`
  ADD CONSTRAINT `brgrusak_ibfk_1` FOREIGN KEY (`idbarang`) REFERENCES `barang` (`idbarang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
