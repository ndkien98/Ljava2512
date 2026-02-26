
-- 1. Bảng LopHoc (Danh mục lớp học):
-- •	MaLop: Kiểu số nguyên, Khóa chính, Tự động tăng.
-- •	TenLop: Kiểu chuỗi (50 ký tự), Không được để trống, Giá trị là duy nhất (Unique).

CREATE TABLE lop_hoc (
                         ma_lop int primary key auto_increment, -- int: kiểu dữ liệu, primary key: đánh dấu đây là khóa chính, auto_increment: tự động tăng giá trị
                         ten_lop nvarchar(50) not null unique -- nvarchar(50): kiểu dữ liệu chuỗi có độ dài tối đa 50 ký tự và có thể viết tiếng việt,
    -- not null: không được để trống, unique: giá trị phải là duy nhất => đây là các constraint (ràng buộc) để đảm bảo tính toàn vẹn dữ liệu
);

-- 2 Bảng SinhVien (Danh mục sinh viên):
CREATE table sinh_vien (
                           ma_sv int primary key auto_increment, -- int: kiểu dữ liệu, primary key: đánh dấu đây là khóa chính, auto_increment: tự động tăng giá trị
                           ho_ten nvarchar(100) not null, -- nvarchar(50): kiểu dữ liệu chuỗi có độ dài tối đa 100 ký tự và có thể viết tiếng việt, not null: không được để trống
                           ngay_sinh date not null, -- date: kiểu dữ liệu ngày tháng, not null: không được để trống
                           email nvarchar(100) not null unique, -- nvarchar(100): kiểu dữ liệu chuỗi có độ dài tối đa 100 ký tự và có thể viết tiếng việt, not null: không được để trống, unique: giá trị phải là duy nhất
                           hoc_phi decimal(10, 2) not null, -- decimal(10, 2): kiểu dữ liệu số thập phân với tổng cộng 10 chữ số và 2 chữ số sau dấu phẩy, not null: không được để trống
                           ma_lop int, -- int: kiểu dữ liệu số nguyên, not null: không được để trống
                           foreign key (ma_lop) references lop_hoc(ma_lop) -- foreign key: câu lệnh xác định khóa ngoại, (ma_lop): cột khóa ngoại trong bảng sinh_vien, references lop_hoc(ma_lop): tham chiếu đến cột ma_lop trong bảng lop_hoc
);


insert into lop_hoc (ten_lop) value ('Java Backend'); -- câu lệnh thêm mới 1 bản ghi dữ liệu (row) vào table lop_hoc
-- insert nhiều row cùng 1 lúc trong 1 table
insert into lop_hoc (ten_lop) values
                                  ('Fontend React'),('Data Analyst'),('Mobile Flutter');

-- sử dụng câu lệnh select để kiểm tra dữ liệu đã insert
select * from lop_hoc;

-- insert dữ liệu vào bảng sinh_vien
# •	SV1: Nguyen Van Anh | 15/05/2000 | anhnv@gmail.com | 2500 | Lớp: Java Backend
# •	SV2: Tran Quang Huy | 20/10/1998 | huytq@gmail.com | 4500 | Lớp: Java Backend
# •	SV3: Le Thi Mai | 12/01/2001 | mailt@gmail.com | 1500 | Lớp: Frontend React
# •	SV4: Pham Quang Minh | 30/12/1999 | minhpq@gmail.com | 3500 | Lớp: Frontend React
# •	SV5: Vu Hoang Yen | 25/03/2002 | yenvh@gmail.com | 5500 | Lớp: Java Backend
# •	SV6: Nguyen Ngoc An | 08/08/2000 | annn@gmail.com | 2000 | Lớp: Data Analyst
# •	SV7: Do Quang Dat | 18/06/1997 | datdq@gmail.com | 6000 | Lớp: Data Analyst
# •	SV8: Bui Bich Phuong | 22/11/2001 | phuongbb@gmail.com | 4000 | (Để trống MaLop - Sinh viên tự do)

insert into sinh_vien (ho_ten, ngay_sinh, email, hoc_phi, ma_lop) values
('Nguyen Van Anh', '2000-05-15', 'anhnv@gmail.com', 2500, 1), -- 1: MaLop của lớp Java Backend
('Tran Quang Huy', '1998-10-20', 'huytq@gmail.com', 4500, 1), -- 1: MaLop của lớp Java Backend
('Le Thi Mai', '2001-01-12', 'mailt@gmail.com', 1500, 2), -- 2: MaLop của lớp Frontend React
('Pham Quang Minh', '1999-12-30', 'minhpq@gmail.com', 3500, 2), -- 2: MaLop của lớp Frontend React
('Vu Hoang Yen', '2002-03-25', 'yenvh@gmail.com', 5500, 1), -- 1: MaLop của lớp Java Backend
('Nguyen Ngoc An', '2000-08-08', 'annn@gmail.com', 2000, 3), -- 3: MaLop của lớp Data Analyst
('Do Quang Dat', '1997-06-18', 'datdq@gmail.com', 6000, 3), -- 3: MaLop của lớp Data Analyst
('Bui Bich Phuong', '2001-11-22', 'phuongbb@gmail.com', 4000, null); -- null: để trống MaLop - Sinh viên tự do

-- insert into sinh_vien (ho_ten, ngay_sinh, email, hoc_phi, ma_lop) value ('Le Van Long', '2001-09-10', 'longlv@gmail.com', 3000, 10);
-- Câu lệnh này lỗi a foreign key constraint fails (`quanlysinhvien`.`sinh_vien`, CONSTRAINT `sinh_vien_ibfk_1` FOREIGN KEY (`ma_lop`) REFERENCES `lop_hoc` (`ma_lop`))
-- vì MaLop = 10 không tồn tại trong bảng lop_hoc, do đó vi phạm ràng buộc khóa ngoại (foreign key constraint) đã được thiết lập giữa bảng sinh_vien và lop_hoc.

-- select: sử dụng để truy vấn dữ liệu trong 1 table
-- ex
select * from sinh_vien;

-- update: sử dụng để cập nhật dữ liệu trong 1 table
update sinh_vien set ma_lop = 2 where ma_sv = 1; -- câu lệnh update để cập nhật dữ liệu trong bảng sinh_vien, set ma_lop = 2: cập nhật giá trị ma_lop thành 2, where ma_sv = 1: điều kiện để xác định bản ghi cần cập nhật (ở đây là bản ghi có ma_sv = 1)

-- delete: sử dụng để xóa dữ liệu trong 1 table
delete from sinh_vien where ma_sv = 1; -- câu lệnh delete để xóa dữ liệu trong bảng sinh_vien, where ma_sv = 1: điều kiện để xác định bản ghi cần xóa (ở đây là bản ghi có ma_sv = 1)

-- where: sử dụng để lọc dữ liệu theo điều kiện cụ thể, kết hợp với các toán tử so sánh (=, <, >, <=, >=, !=) và toán tử logic (AND, OR, NOT) để tạo ra các điều kiện phức tạp hơn.
select * from sinh_vien where hoc_phi > 3000; -- câu lệnh select để truy vấn dữ liệu từ bảng sinh_vien, where hoc_phi > 3000: điều kiện để lọc các bản ghi có giá trị hoc_phi lớn hơn 3000

-- like: toán tử để tìm kiếm tương đối
select * from sinh_vien where ho_ten like '%An%'; -- tìm kiếm các bản ghi có họ tên chứa chuỗi 'An', %: nếu đứng phía trước chuỗi có nghĩa sẽ bỏ qua toàn bộ các phần tử phía trước nó, % nếu đứng phía sau chuỗi -> bỏ qua toàn bộ các phần tử phía sau nó
select * from sinh_vien where ho_ten like 'Nguyen%'; -- tìm kiếm các bản ghi có họ tên bắt đầu bằng 'Nguyen'
select * from sinh_vien where ho_ten like '%An'; -- tìm kiếm các bản ghi có họ tên kết thúc bằng 'An'

-- top: là version của SQL Server, dùng để giới hạn số lượng bản ghi trả về trong kết quả truy vấn, với mysql sẽ sử dụng limit để giới hạn số lượng bản ghi trả về
select  * from sinh_vien limit 3;

-- distinct: được sử dụng để loại các bản ghi trùng lặp trong kết quả truy vấn trả về
select distinct ma_lop from sinh_vien; -- lọc bỏ các bản ghi trùng lặp trong cột mã lớp

-- ORDER BY: được sử dụng để sắp xếp kết quả truy vấn theo một hặc nhiều cột trong dữ liệu được trả về
select * from sinh_vien order by hoc_phi desc; -- sắp xếp kết quả truy vấn theo cột hoc_phi theo thứ tự giảm dần (desc)
select * from sinh_vien order by ngay_sinh asc; -- sắp xếp kết quả truy vấn theo cột ngay_sinh theo thứ tự tăng dần (asc)

-- GROUP BY: được sử dụng để gom nhóm các bản ghi có cùng giá trị trong một cột với nhau
select count(*),ma_lop from sinh_vien group by ma_lop; -- đếm số luương sinh viên của mỗi mã lớp tương ứng

-- HAVING: được sử dụng để lọc các kết quả tìm kiếm sau khi đã được gom nhóm bởi group by hoặc có thêm where để lọc trước khi group by
select count(*), ma_lop from sinh_vien group by ma_lop having count(*) > 1; -- đểm số lượng sinh viên ở mỗi lớp học có > 1 sinh viên

-- II. Join: sử dụng dựng để kết hợp dữ liệu giữa nhiều table với nhau dựa trên một cột dung giữa chúng, thường là khóa ngoại và khóa chính
-- các loại join: inner join, left join, right join, cross join, self join
-- inner join: trả về dữ liệu của các bản ghi có cùng 1 giá trị khóa ngooại và khóa chính giữa 2 table
-- ex: lấy thông tin sinh viên và tên lớp học của họ
select * from sinh_vien inner join lop_hoc on sinh_vien.ma_lop = lop_hoc.ma_lop;
-- câu lệnh này chỉ lấy ra các bản ghi thỏa mãn điều kiện ma_lop của bảng sinh viên và ma_lop của bảng lop_hoc phải bằng nhau, hay sẽ là tập trung hai table

-- left join: table trước left join gọi là A và table sau left join gọi là B, => left join sẽ trả về tất cả các bản ghi của table A và các bản ghi có giá trị khóa ngoại khớp với khóa chính của table B, nếu các bản ở table A không có bản ghi nào khớp với table B dữ liệu sẽ trả về null cho các cột của table B
select * from sinh_vien left join lop_hoc on sinh_vien.ma_lop = lop_hoc.ma_lop;
-- câu lệnh này sẽ trả về tất cả các bản ghi của bảng sinh_vien và các bản ghi có giá trị ma_lop khớp với ma_lop của bảng lop_hoc, nếu có bản ghi nào trong bảng sinh_vien không có giá trị ma_lop khớp với bảng lop_hoc thì các cột của bảng lop_hoc sẽ trả về null

-- right join: table trước right join gọi là A và table sau right join gọi là B, => right join sẽ trả về tất cả các bản ghi của table B và các bản ghi có giá trị khóa ngoại khớp với khóa chính của table A, nếu các bản ở table B không có bản ghi nào khớp với table A dữ liệu sẽ trả về null cho các cột của table A
select * from sinh_vien right join lop_hoc on sinh_vien.ma_lop = lop_hoc.ma_lop;