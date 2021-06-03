-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th2 07, 2020 lúc 01:08 PM
-- Phiên bản máy phục vụ: 10.4.10-MariaDB
-- Phiên bản PHP: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `demoshop_db`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tb_brands`
--

CREATE TABLE `tb_brands` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `images` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tb_brands`
--

INSERT INTO `tb_brands` (`id`, `name`, `images`) VALUES
(1, 'Điện thoại', 'https://www.freeiconspng.com/uploads/mobile-phone-cell-icon-25.png'),
(2, 'Laptop', 'http://icons.iconarchive.com/icons/custom-icon-design/flatastic-7/512/Laptop-icon.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tb_customers`
--

CREATE TABLE `tb_customers` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tb_customers`
--

INSERT INTO `tb_customers` (`id`, `name`, `phone`, `email`) VALUES
(1, 'abcxyz', 'phone', 'email'),
(2, 'abcxyz', 'phone', 'email');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tb_orderdetails`
--

CREATE TABLE `tb_orderdetails` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_name` varchar(2500) COLLATE utf8_unicode_ci NOT NULL,
  `product_price` int(11) NOT NULL,
  `product_amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tb_products`
--

CREATE TABLE `tb_products` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `price` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `images` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(2500) COLLATE utf8_unicode_ci NOT NULL,
  `brand_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tb_products`
--

INSERT INTO `tb_products` (`id`, `name`, `price`, `images`, `description`, `brand_id`) VALUES
(1, 'iphone 7', '10950999', 'http://hoangphi.works/upload/photo/phone_14_01_2020_gPyJ6_80404613_1690093101146650_3291862986641113088_o.jpg', 'mô tả iphone 7', 1),
(2, 'Dell ins 3558', '13598000', 'http://hoangphi.works/upload/photo/phone_14_01_2020_3wRxB_77146608_1658428457646448_7482861652824555520_o.jpg', 'Laptop dell ins 3558', 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tb_brands`
--
ALTER TABLE `tb_brands`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tb_customers`
--
ALTER TABLE `tb_customers`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tb_orderdetails`
--
ALTER TABLE `tb_orderdetails`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tb_products`
--
ALTER TABLE `tb_products`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tb_brands`
--
ALTER TABLE `tb_brands`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `tb_customers`
--
ALTER TABLE `tb_customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `tb_orderdetails`
--
ALTER TABLE `tb_orderdetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `tb_products`
--
ALTER TABLE `tb_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
