
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
use quanlysinhvien;
select * from sinh_vien inner join lop_hoc on sinh_vien.ma_lop = lop_hoc.ma_lop;
-- câu lệnh này chỉ lấy ra các bản ghi thỏa mãn điều kiện ma_lop của bảng sinh viên và ma_lop của bảng lop_hoc phải bằng nhau, hay sẽ là tập trung hai table

-- left join: table trước left join gọi là A và table sau left join gọi là B, => left join sẽ trả về tất cả các bản ghi của table A và các bản ghi có giá trị khóa ngoại khớp với khóa chính của table B, nếu các bản ở table A không có bản ghi nào khớp với table B dữ liệu sẽ trả về null cho các cột của table B
select * from sinh_vien left join lop_hoc on sinh_vien.ma_lop = lop_hoc.ma_lop;
-- câu lệnh này sẽ trả về tất cả các bản ghi của bảng sinh_vien và các bản ghi có giá trị ma_lop khớp với ma_lop của bảng lop_hoc, nếu có bản ghi nào trong bảng sinh_vien không có giá trị ma_lop khớp với bảng lop_hoc thì các cột của bảng lop_hoc sẽ trả về null

-- right join: table trước right join gọi là A và table sau right join gọi là B, => right join sẽ trả về tất cả các bản ghi của table B và các bản ghi có giá trị khóa ngoại khớp với khóa chính của table A, nếu các bản ở table B không có bản ghi nào khớp với table A dữ liệu sẽ trả về null cho các cột của table A
select * from sinh_vien right join lop_hoc on sinh_vien.ma_lop = lop_hoc.ma_lop;

-- cross join: trả về tích Đề-các (Cartesian product) của hai bảng, nghĩa là kết hợp mỗi bản ghi của bảng A với mỗi bản ghi của bảng B, không cần điều kiện nào giữa hai bảng
select * from sinh_vien cross join lop_hoc;

select count(*) from sinh_vien;
select count(*) from lop_hoc;

-- self join: là một trường hợp đặc biệt của join, trong đó một bảng được kết nối với chính nó, thường được sử dụng để biểu diễn mối quan hệ phân cấp hoặc để so sánh các bản ghi trong cùng một bảng
-- ex: bài toán lưu trữ các môn học. Các danh mục các loại môn học. Môn học tự nhiên, môn học xã hội, môn học năng khiếu
-- Môn học tự nhiên: Toán, Lý, Hóa
-- Môn học xã hội: Văn, Sử, Địa
-- Môn học năng khiếu: Thể dục, Âm nhạc, Mỹ thuật
-- Toán: Toán 10, Toán 11, Toán 12
-- Lý: Lý 10, Lý 11, Lý 12
-- Hóa: Hóa 10, Hóa 11, Hóa 12
-- Văn: Văn 10, Văn 11, Văn 12
-- Sử: Sử 10, Sử 11, Sử 12
-- Thiết kế table lưu trữ danh mục lớp học. Trong đó các danh mục con sẽ tham triếu trực tiếp đến danh mục cha của nó
create table danh_muc_mon_hoc (
    id int primary key auto_increment,
    ten_danh_muc nvarchar(50) not null,
    parent_id int, -- parent_id là khóa ngoại tham chiếu đến id của chính bảng danh_muc_mon_hoc để biểu diễn mối quan hệ phân cấp giữa các danh mục môn học
    foreign key (parent_id) references danh_muc_mon_hoc(id) -- foreign key (parent_id): cột khóa ngoại trong bảng danh_muc_mon_hoc, references danh_muc_mon_hoc(id): tham chiếu đến cột id của chính bảng danh_muc_mon_hoc
);

insert into danh_muc_mon_hoc(ten_danh_muc, parent_id) values
('Môn học tự nhiên', null), -- null: danh mục cha không có parent_id nào vì nó là danh mục gốc
('Môn học xã hội', null),
('Môn học năng khiếu', null),
('Toán', 1), -- 1: parent_id tham chiếu đến id của danh mục cha 'Môn học tự nhiên'
('Lý', 1),
('Hóa', 1),
('Văn', 2), -- 2: parent_id tham chiếu đến id của danh mục cha 'Môn học xã hội'
('Sử', 2),
('Địa', 2),
('Thể dục', 3), -- 3: parent_id tham chiếu đến id của danh mục cha 'Môn học năng khiếu'
('Âm nhạc', 3),
('Mỹ thuật', 3),
('Toán 10', 4), -- 4: parent_id tham chiếu đến id của danh mục cha 'Toán');
('Toán 11', 4),
('Toán 12', 4),
('Lý 10', 5), -- 5: parent_id tham chiếu đến id của danh mục cha 'Lý'
('Lý 11', 5),
('Lý 12', 5),
('Hóa 10', 6), -- 6: parent_id tham chiếu đến id của danh mục cha 'Hóa'
('Hóa 11', 6),
('Hóa 12', 6),
('Văn 10', 7), -- 7: parent_id tham chiếu đến id của danh mục cha 'Văn'
('Văn 11', 7),
('Văn 12', 7),
('Sử 10', 8), -- 8: parent_id tham chiếu đến id của danh mục cha 'Sử'
('Sử 11', 8),
('Sử 12', 8);

-- Lấy ra thông tin các môn học và tên danh mục cha của môn học đó
select danh_muc_con.ten_danh_muc as ten_danh_muc_con,danh_muc_cha.ten_danh_muc as danh_muc_cha
from danh_muc_mon_hoc danh_muc_con left join danh_muc_mon_hoc danh_muc_cha on danh_muc_con.parent_id = danh_muc_cha.id;

-- Các mối quan hệ giữa các table:
-- 1. Quan hệ một-nhiều (one-to-many): một bản ghi trong bảng cha có thể liên kết với nhau bản ghi trong bảng con, nhưng mỗi bản ghi trong bảng con chỉ liên kết với một bản ghi trong bảng cha.
-- Ví dụ: một lớp học (bảng lop_hoc) có thể có nhiều sinh viên (bảng sinh_vien), nhưng mỗi sinh viên chỉ thuộc về một lớp học.
-- cũng là nghịch đảo của many to one: nhiều bản ghi trong bảng con liên kết với một bản ghi trong bảng cha, nhưng một bản ghi trong bảng cha chỉ liên kết với một bản ghi trong bảng con

-- 2. Mối quan hệ nhiều-nhiều (many-to-many): một bản ghi trong bảng A có thể liên kết với nhiều bản ghi trong bảng B,
-- và ngược lại, một bản ghi trong bảng B cũng có thể liên kết với nhiều bản ghi trong bảng A.
-- Để biểu diễn mối quan hệ này, chúng ta thường sử dụng một bảng trung gian (junction table) để lưu trữ các liên kết giữa hai bảng chính.
-- ví dụ: một sinh viên có thể học nhiều môn học, mỗi môn học học sinh học sẽ có điểm riêng, một môn học có thể có nhiều học sinh học
-- => Mối quan hệ giữa sinh_vien và danh_muc_mon_hoc là nhiều-nhiều,
-- để biểu diễn mối quan hệ này chúng ta sẽ tạo một bảng trung gian có tên là bang_diem để lưu trữ các liên kết giữa sinh_vien và danh_muc_mon_hoc,

create table bang_diem (
    id int primary key auto_increment,
    ma_sv int, -- ma_sv là khóa ngoại tham chiếu đến ma_sv của bảng sinh_vien
    ma_mon_hoc int, -- ma_mon_hoc là khóa ngoại tham chiếu đến id của bảng danh_muc_mon_hoc
    diem decimal(5, 2), -- điểm số của sinh viên trong môn học đó
    foreign key (ma_sv) references sinh_vien(ma_sv), -- foreign key (ma_sv): cột khóa ngoại trong bảng bang_diem, references sinh_vien(ma_sv): tham chiếu đến cột ma_sv của bảng sinh_vien
    foreign key (ma_mon_hoc) references danh_muc_mon_hoc(id) -- foreign key (ma_mon_hoc): cột khóa ngoại trong bảng bang_diem, references danh_muc_mon_hoc(id): tham chiếu đến cột id của bảng danh_muc_mon_hoc
);

insert bang_diem (ma_sv, ma_mon_hoc, diem) values
(2, 13, 8.5), -- SV2 học môn Toán 10 được 8.5 điểm
(2, 14, 9.0), -- SV2 học môn Toán 11 được 9.0 điểm
(3, 13, 7.0), -- SV3 học môn Toán 10 được 7.0 điểm
(3, 14, 7.5), -- SV3 học môn Toán 11 được 7.5 điểm
(4, 15, 6.0), -- SV4 học môn Toán 12 được 6.0 điểm
(4, 16, 6.5); -- SV4 học môn Lý 10 được 6.5 điểm


-- Chiến lược thực thi của 1 câu lệnh sql:
-- 1. Từ khóa FROM: xác định bảng dữ liệu chính mà câu lệnh sẽ truy vấn, có thể bao gồm nhiều bảng nếu có sử dụng các loại join,
-- 2. Từ khóa WHERE: lọc các bản ghi dựa trên điều kiện được chỉ định, chỉ những
-- danh sách các bản ghi thỏa mãn điều kiện trong mệnh đề WHERE mới được đưa vào quá trình xử lý tiếp theo
-- 3. Từ khóa GROUP BY: nếu có, sẽ nhóm các bản ghi lại với nhau dựa trên giá trị của một hoặc nhiều cột, mỗi nhóm sẽ được xử lý như một thực thể riêng biệt trong quá trình thực thi tiếp theo
-- 4. Từ khóa HAVING: nếu có, sẽ lọc các nhóm được tạo bởi GROUP BY dựa trên điều kiện được chỉ định, chỉ những
-- 5. Từ khóa SELECT: xác định các cột dữ liệu sẽ được trả về trong kết quả truy vấn, chỉ những cột được liệt kê trong mệnh đề SELECT mới được đưa vào kết quả cuối cùng
-- 6. Từ khóa ORDER BY: nếu có, sẽ sắp xếp kết quả truy vấn dựa trên một hoặc nhiều cột, theo thứ tự tăng dần (ASC) hoặc giảm dần (DESC)

-- Chiến lược thực thi quyest các bản ghi của câu sql trong bảng lưu trữ dữ liệu cụ thể:
-- mysql thực hiện quyets toàn bộ dữ liệu được lưu trong bảng, sau đó áp dụng các điều kiện lọc được chỉ định từ mệnh đề để lọc bớt các dữ liệu
-- Như vậy nếu dữ liệu trong table quá lớn sẽ làm cho quá trình thực thi câu lệnh cực kỳ chậm nếu kết hợp join hoặc sub query giữa các table còn làm cho số lượnc
-- bản ghi cần được xử lý tăng lên rất nhiều lần làm cho query chạy chậm và có thể sẽ block lại các cụm dữ liệu cho đến toàn bộ table dữ liệu. làm cho các query \
-- khác bị ảnh hưởng dẫn đến hệ thống chạy chậm hoặc bị treo
-- => để giúp cơ sở dữ liệu tối ưu hiệu năng, tìm kiếm dữ liệu nhanh hơn database tạo ra cơ chế index cho các cột của table. Giúp database tìm kiếm dữ liệu 1 cách nhanh hơn

-- index: là một cấu trúc dữ liệu đặc biệt được tạo ra để tăng tốc độ truy vấn dữ liệu trong một bảng,
              -- nó hoạt động giống như một bảng tra cứu giúp cơ sở dữ liệu tìm kiếm dữ liệu nhanh hơn mà không cần phải quét toàn bộ bảng
-- index được tạo trên một hoặc nhiều cột của bảng, khi một truy vấn được thực hiện với điều kiện lọc dựa trên các cột đã được đánh index
-- các cấu trúc dữ liệu của index:
-- 1. B-tree (Balanced Tree): là cấu trúc dữ liệu phổ biến nhất được sử dụng cho index trong các hệ quản trị cơ sở dữ liệu quan hệ, nó tổ chức dữ liệu theo dạng cây cân bằng, giúp tối ưu hóa việc tìm kiếm, chèn và xóa dữ liệu
-- 2. hash map: cấu trúc dữ liệu giúp đánh chỉ mục dạng key trong 1 array các bucket, giúp cho việc tìm kiếm theo key trở lên nhanh hơn rất nhiều


explain select * from sinh_vien;

select * from sinh_vien;

explain select * from sinh_vien where ma_sv = 1;

explain select * from sinh_vien where ho_ten = 'Vu Hoang Yen';

explain select * from sinh_vien where email = 'yenvh@gmail.com';

-- Tại sao không đánh index cho tất cả các cột trong bảng?