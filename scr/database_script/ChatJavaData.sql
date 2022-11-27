USE [ChatJava]
GO
INSERT [dbo].[TaiKhoan] ([Username], [Password], [Email], [NgaySinh], [GioiTinh], [DiaChi], [TrangThai]) VALUES (N'bebaoboy', N'1234567', N'bebaoboy@gmail.com', NULL, 0, NULL, 1)
GO
INSERT [dbo].[TaiKhoan] ([Username], [Password], [Email], [NgaySinh], [GioiTinh], [DiaChi], [TrangThai]) VALUES (N'kimthanh', N'kimthanh123', N'maithikimthanh@gmail.com', CAST(N'2001-12-06' AS Date), 1, N'1 Lê Lợi Q5', 0)
GO
INSERT [dbo].[TaiKhoan] ([Username], [Password], [Email], [NgaySinh], [GioiTinh], [DiaChi], [TrangThai]) VALUES (N'kimthanh2', N'kimthanh123', N'maithikimthanh@gmail.com', CAST(N'2002-01-01' AS Date), 1, N'1 Lê Lợi Q5', 0)
GO
INSERT [dbo].[TaiKhoan] ([Username], [Password], [Email], [NgaySinh], [GioiTinh], [DiaChi], [TrangThai]) VALUES (N'luutuanquan', N'9999999', N'tuanquan127@gmail.com', CAST(N'2002-08-10' AS Date), 0, N'Hai Bà Trưng', 0)
GO
INSERT [dbo].[TaiKhoan] ([Username], [Password], [Email], [NgaySinh], [GioiTinh], [DiaChi], [TrangThai]) VALUES (N'minhphu', N'ddmuri593', N'minhphu@gmail.com', CAST(N'2002-10-23' AS Date), 0, N'Thủ Đức', 0)
GO
INSERT [dbo].[TaiKhoan] ([Username], [Password], [Email], [NgaySinh], [GioiTinh], [DiaChi], [TrangThai]) VALUES (N'reika', N'246810', N'ireka198@gmail.com', CAST(N'2001-12-06' AS Date), 1, N'Nguyễn Văn Cừ', 0)
GO
INSERT [dbo].[TaiKhoan] ([Username], [Password], [Email], [NgaySinh], [GioiTinh], [DiaChi], [TrangThai]) VALUES (N'trungluong', N'phamtrantrungluong', N'phamtrantrungluong@gmail.com', CAST(N'1989-12-13' AS Date), 0, N'Trần Hưng Đạo, Q5', 0)
GO
INSERT [dbo].[DanhSachBanBe] ([UsernameChinh], [UsernameBanBe], [NgayKetBan]) VALUES (N'bebaoboy', N'kimthanh', CAST(N'2022-10-16T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[DanhSachBanBe] ([UsernameChinh], [UsernameBanBe], [NgayKetBan]) VALUES (N'bebaoboy', N'luutuanquan', CAST(N'2022-10-12T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[DanhSachBanBe] ([UsernameChinh], [UsernameBanBe], [NgayKetBan]) VALUES (N'bebaoboy', N'trungluong', CAST(N'2022-10-24T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[DanhSachBanBe] ([UsernameChinh], [UsernameBanBe], [NgayKetBan]) VALUES (N'kimthanh', N'bebaoboy', CAST(N'2022-10-26T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[DanhSachBanBe] ([UsernameChinh], [UsernameBanBe], [NgayKetBan]) VALUES (N'kimthanh', N'luutuanquan', CAST(N'2022-10-27T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[DanhSachBanBe] ([UsernameChinh], [UsernameBanBe], [NgayKetBan]) VALUES (N'luutuanquan', N'bebaoboy', CAST(N'2022-10-15T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[DanhSachBanBe] ([UsernameChinh], [UsernameBanBe], [NgayKetBan]) VALUES (N'luutuanquan', N'minhphu', CAST(N'2022-10-20T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[DanhSachBanBe] ([UsernameChinh], [UsernameBanBe], [NgayKetBan]) VALUES (N'luutuanquan', N'reika', CAST(N'2022-10-20T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[DanhSachBanBe] ([UsernameChinh], [UsernameBanBe], [NgayKetBan]) VALUES (N'luutuanquan', N'trungluong', CAST(N'2022-11-07T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[DanhSachBanBe] ([UsernameChinh], [UsernameBanBe], [NgayKetBan]) VALUES (N'minhphu', N'bebaoboy', CAST(N'2022-11-15T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[DanhSachBanBe] ([UsernameChinh], [UsernameBanBe], [NgayKetBan]) VALUES (N'reika', N'kimthanh', CAST(N'2022-10-28T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[DanhSachBanBe] ([UsernameChinh], [UsernameBanBe], [NgayKetBan]) VALUES (N'trungluong', N'luutuanquan', CAST(N'2022-11-05T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[LichSuDangNhap] ([Username], [NgayDangNhap], [NgayDangXuat]) VALUES (N'bebaoboy', CAST(N'2022-10-12T09:00:00.000' AS DateTime), CAST(N'2022-10-12T11:00:00.000' AS DateTime))
GO
INSERT [dbo].[LichSuDangNhap] ([Username], [NgayDangNhap], [NgayDangXuat]) VALUES (N'bebaoboy', CAST(N'2022-10-16T00:00:00.000' AS DateTime), CAST(N'2022-10-16T01:00:00.000' AS DateTime))
GO
INSERT [dbo].[LichSuDangNhap] ([Username], [NgayDangNhap], [NgayDangXuat]) VALUES (N'kimthanh', CAST(N'2022-10-26T12:00:00.000' AS DateTime), CAST(N'2022-10-26T13:00:00.000' AS DateTime))
GO
INSERT [dbo].[LichSuDangNhap] ([Username], [NgayDangNhap], [NgayDangXuat]) VALUES (N'kimthanh', CAST(N'2022-10-27T11:00:00.000' AS DateTime), CAST(N'2022-10-27T14:00:00.000' AS DateTime))
GO
INSERT [dbo].[LichSuDangNhap] ([Username], [NgayDangNhap], [NgayDangXuat]) VALUES (N'luutuanquan', CAST(N'2022-10-15T10:00:00.000' AS DateTime), CAST(N'2022-10-15T10:30:00.000' AS DateTime))
GO
INSERT [dbo].[LichSuDangNhap] ([Username], [NgayDangNhap], [NgayDangXuat]) VALUES (N'luutuanquan', CAST(N'2022-10-20T07:00:00.000' AS DateTime), CAST(N'2022-10-20T11:00:00.000' AS DateTime))
GO
INSERT [dbo].[LichSuDangNhap] ([Username], [NgayDangNhap], [NgayDangXuat]) VALUES (N'luutuanquan', CAST(N'2022-10-27T06:30:00.000' AS DateTime), CAST(N'2022-10-27T04:00:00.000' AS DateTime))
GO
INSERT [dbo].[LichSuDangNhap] ([Username], [NgayDangNhap], [NgayDangXuat]) VALUES (N'luutuanquan', CAST(N'2022-11-07T16:00:00.000' AS DateTime), CAST(N'2022-11-07T20:00:00.000' AS DateTime))
GO
INSERT [dbo].[LichSuDangNhap] ([Username], [NgayDangNhap], [NgayDangXuat]) VALUES (N'minhphu', CAST(N'2022-11-15T19:00:00.000' AS DateTime), CAST(N'2022-11-15T19:30:00.000' AS DateTime))
GO
INSERT [dbo].[LichSuDangNhap] ([Username], [NgayDangNhap], [NgayDangXuat]) VALUES (N'reika', CAST(N'2022-10-28T08:00:00.000' AS DateTime), CAST(N'2022-10-28T15:00:00.000' AS DateTime))
GO
INSERT [dbo].[LichSuDangNhap] ([Username], [NgayDangNhap], [NgayDangXuat]) VALUES (N'trungluong', CAST(N'2022-11-05T18:00:00.000' AS DateTime), CAST(N'2022-11-05T11:00:00.000' AS DateTime))
GO
