import config.InputMethods;
import model.Catalog;
import model.Product;
import servic.CatalogService;
import servic.ProductService;

import java.util.Scanner;

public class Main {
    static CatalogService catalogService=new CatalogService();
    static ProductService productService=new ProductService();
    public static void main(String[] args) {

        int choice;
        boolean exit = false;

        do {
            System.out.println("**************************BASIC-MENU**************************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("**************************************************************");
            System.out.println("Chọn một tùy chọn (1-3): ");
            choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    catalogManagementMenu();
                    break;
                case 2:
                    productManagementMenu();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng chọn lại!");
            }
        } while (!exit);
    }

    public static void catalogManagementMenu() {

        int choice;
        boolean back = false;

        do {
            System.out.println("********************CATALOG-MANAGEMENT********************");
            System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
            System.out.println("2. Hiển thị thông tin tất cả các danh mục");
            System.out.println("3. Sửa tên danh mục theo mã danh mục");
            System.out.println("4. Xóa danh mục theo mã danh mục (lưu ý không xóa khi có sản phẩm)");
            System.out.println("5. Quay lại");
            System.out.println("*********************************************************");
            System.out.println("Chọn một tùy chọn (1-5): ");
            choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    themmoidanhmuc();

                    break;
                case 2:
                    // Hiển thị danh mục
              hienthidanhmuc();
                    break;
                case 3:
                    // Sửa tên danh mục
          suadanhmuc();
                    break;
                case 4:
                    // Xóa danh mục
         xoadanhmuc();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng chọn lại!");
            }
        } while (!back);
    }
    public static void themmoidanhmuc() {
        System.out.println("mời bạn nhập số lượng danh mục muốn tạo");
        int n = InputMethods.getPositiveInteger();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho danh mục thứ " + (i + 1));
            Catalog themmoi = new Catalog();
            // id tự tăng
            int newId = catalogService.getNewId();
            themmoi.setCatalogId(newId);
            System.out.println("New ID : " + newId);
            System.out.println("nhập vào name");
themmoi.setCatalogName(InputMethods.getString());

            catalogService.save(themmoi);
        }
    }
    public static void hienthidanhmuc() {
        if (catalogService.getSize() == 0) {
            System.err.println("Không có danh mục nào ");
            return;
        }

        for (Catalog singer : catalogService.getAll()
        ) {
            System.out.println(singer);
        }
    }
    public static void suadanhmuc() {
        System.out.println("Nhập vào id cần sửa");
        int idEdit = InputMethods.getInteger();
        Catalog editdanhmuc = catalogService.findById(idEdit);

        if(editdanhmuc ==null){
            System.err.println("Không tìm thấy danh mục ");
            return;
        }
        System.out.println("Đối tượng cần sửa là :");
        System.out.println(editdanhmuc);
//         cho phép sửa
        Catalog updatedanhmuc = new Catalog();
        updatedanhmuc.setCatalogId(idEdit);
        System.out.println("nhập vào name");
        updatedanhmuc.setCatalogName(InputMethods.getString());

        catalogService.save(updatedanhmuc);
    }
    public static void xoadanhmuc(){
        System.out.println("nhập vào id cần xóa ");
        int idDel = InputMethods.getInteger();
        catalogService.delete(idDel);
    }

    public static void productManagementMenu() {

        int choice;
        boolean back = false;

        do {
            System.out.println("********************PRODUCT-MANAGEMENT********************");
            System.out.println("1. Nhập số sản phẩm và nhập thông tin sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
            System.out.println("4. Xóa sản phẩm theo mã");
            System.out.println("5. Tìm kiếm sách theo tên sách");
            System.out.println("6. Thay đổi thông tin của sách theo mã sách");
            System.out.println("7. Quay lại");
            System.out.println("*********************************************************");
            System.out.println("Chọn một tùy chọn (1-7): ");
            choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    // Thêm mới sản phẩm
                 themmoisanpham();
                    break;
                case 2:
                    // Hiển thị sản phẩm
            hienthisanpham();
                    break;
                case 3:
                    // Sắp xếp sản phẩm
                sapxepgiamdansp();
                    break;
                case 4:
                    // Xóa sản phẩm
              xoasanpham();
                    break;
                case 5:
                    // Tìm kiếm sách
                timkiemsachtheoten();
                    break;
                case 6:
                    // Thay đổi thông tin sách
               thaydoithongtin();
                    break;
                case 7:
                    back = true;
                    break;
                default:
                    System.out.println("Tùy chọn không hợp lệ. Vui lòng chọn lại!");
            }
        } while (!back);
    }
    public static void themmoisanpham() {

        if (catalogService.getSize() == 0) {
            System.err.println("Không có danh mục nào , vui lóng quay ại thêm danh mục trước");
            return;
        }
        System.out.println("Nhập vào số lượng cần thêm mới");
        int n = InputMethods.getInteger();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho sản phẩm thứ " + (i + 1));
            Product themmoi = new Product();


            while (true){
                System.out.println("mời bạn nhập vào id san phẩm");
              String  idsp=InputMethods.getproduct();
                if (productService.findById(idsp)==null){

                    themmoi.setProductid(idsp);
                    break;

                }else continue;

            }

            System.out.println("mời bạn nhập vào tên sp");
            themmoi.setProductName(InputMethods.getString());
            System.out.println("mời bạn nhập vào giá sản phâm");
            themmoi.setProductPrice(InputMethods.getgiasp());
            System.out.println(("mòi bạn nhập vào mô tả"));
            themmoi.setDescription(InputMethods.getString());
            System.out.println(("mòi bạn nhập vào số lượng"));
            themmoi.setStock(InputMethods.getslsp());
            for (Catalog c: catalogService.getAll()
                 ) {
                System.out.println(c);

            }
            System.out.println("mời bạn chọn danh mục cho sản phẩm bằng cách nhập id");
            int id=InputMethods.getInteger();
themmoi.setCatalog(catalogService.findById(id));

            // tiến hành them mới vào mảng
            productService.save(themmoi);
        }
    }


    public static void hienthisanpham() {
        if (productService.getSize() == 0) {
            System.err.println("Không có sản phẩm nào ");
            return;
        }
        for (Product sp : productService.getAll()) {
            System.out.println(sp);
        }
    }

    // up date thông tin
    public static void thaydoithongtin() {
        System.out.println("Nhập vào id cần sửa");
        String idEdit = InputMethods.getString();
        Product sua = productService.findById(idEdit);
        if(sua ==null){
            System.err.println("Không tìm thấy san pham ");
            return;
        }
        Product themmoi=new Product();
        // cho phép sửa
        System.out.println("mời bạn nhập vào tên sp");
        themmoi.setProductName(InputMethods.getString());
        System.out.println("mời bạn nhập vào giá sản phâm");
        themmoi.setProductPrice(InputMethods.getgiasp());
        System.out.println(("mòi bạn nhập vào mô tả"));
        themmoi.setDescription(InputMethods.getString());
        System.out.println(("mòi bạn nhập vào số lượng"));
        themmoi.setStock(InputMethods.getslsp());
        themmoi.setProductid(idEdit);
        for (Catalog c: catalogService.getAll()
        ) {
            System.out.println(c);

        }
        System.out.println("mời bạn chọn danh mục cho sản phẩm bằng cách nhập id");
        int id=InputMethods.getInteger();
        themmoi.setCatalog(catalogService.findById(id));

        // tiến hành them mới vào mảng
        productService.save(themmoi);
    }
    public static void xoasanpham(){
        System.out.println("nhập vào id cần xóa ");
        String idDel = InputMethods.getString();
        productService.delete(idDel);
    }

    public static void timkiemsachtheoten() {
        System.out.println("nhập vào tên sách bạn muốn tìm kiếm");
        String name=InputMethods.getString();
        int check=-1;
        for (Product sp: productService.getAll()
             ) { if(sp.getProductName().contains(name)){
            System.out.println(sp);
            check=1;
        }

        }
        if (check==-1){
            System.out.println("không tìm thấy sách");
        }

    }
    public static void sapxepgiamdansp() {
productService.getAll().sort((a,b)-> (int) (a.getProductPrice()-b.getProductPrice()));
    }

}
