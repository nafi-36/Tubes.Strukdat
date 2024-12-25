import java.util.Scanner;

class Admin {
    private int id;
    private String username;
    private String password;

    public Admin() {
        this.id = 1;
        this.username = "admin";
        this.password = "admin123";
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Karyawan {
    private int id;
    private String nama;
    private String nip;
    private String username;
    private String password;

    public Karyawan(int id, String nama, String nip, String username, String password) {
        this.id = id;
        this.nama = nama;
        this.nip = nip;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNip() {
        return nip;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return id + " " + nama + " " + nip;
    }
}

class Mobil {
    private int id;
    private String nama;
    private String jenisMobil;
    private String nopol;
    private int kapasitas;

    public Mobil(int id, String nama, String jenisMobil, String nopol, int kapasitas) {
        this.id = id;
        this.nama = nama;
        this.jenisMobil = jenisMobil;
        this.nopol = nopol;
        this.kapasitas = kapasitas;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJenisMobil() {
        return jenisMobil;
    }

    public String getNopol() {
        return nopol;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public String toString() {
        return id + " " + nama + " " + jenisMobil + " " + nopol + " " + kapasitas;
    }
}

class Peminjaman {
    private int id;
    private NodeKaryawan karyawan;
    private String tanggalPeminjaman;
    private String status;
    private SLLDetailPeminjaman detailPeminjaman;

    public Peminjaman(int id, NodeKaryawan karyawan, String tanggalPeminjaman, String status) {
        this.id = id;
        this.karyawan = karyawan;
        this.tanggalPeminjaman = tanggalPeminjaman;
        this.status = status;
        this.detailPeminjaman = new SLLDetailPeminjaman();
    }

    public int getId() {
        return id;
    }

    public NodeKaryawan getKaryawan() {
        return karyawan;
    }

    public String getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }

    public String getStatus() {
        return status;
    }

    public SLLDetailPeminjaman getDetailPeminjaman() {
        return detailPeminjaman;
    }

    public void addDetailPeminjaman(NodeDetailPeminjaman detail) {
        this.detailPeminjaman.insert(detail.data);
    }

    public String toString() {
        return id + " " + karyawan + " " + tanggalPeminjaman + " " + status + "\nDetail Peminjaman: \n"
                + detailPeminjaman;
    }
}

class DetailPeminjaman {
    private int id;
    private NodeMobil mobil;
    private String tanggalPengembalian;
    private String statusPengembalian;

    public DetailPeminjaman(int id, NodeMobil mobil, String tanggalPengembalian, String statusPengembalian) {
        this.id = id;
        this.mobil = mobil;
        this.tanggalPengembalian = tanggalPengembalian;
        this.statusPengembalian = statusPengembalian;
    }

    public int getId() {
        return id;
    }

    public NodeMobil getMobil() {
        return mobil;
    }

    public String getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public String getStatusPengembalian() {
        return statusPengembalian;
    }

    public String toString() {
        return id + " " + mobil + " " + tanggalPengembalian + " " + statusPengembalian;
    }
}

// kelas node untuk karyawan
class NodeKaryawan {
    Karyawan data;
    NodeKaryawan next;

    public NodeKaryawan(Karyawan data) {
        this.data = data;
        this.next = null;
    }

    public String toString() {
        return data.toString();
    }
}

// kelas node untuk mobil
class NodeMobil {
    Mobil data;
    NodeMobil next;

    public NodeMobil(Mobil data) {
        this.data = data;
        this.next = null;
    }

    public String toString() {
        return data.toString();
    }
}

// kelas node untuk peminjaman
class NodePeminjaman {
    Peminjaman data;
    NodePeminjaman next;

    public NodePeminjaman(Peminjaman data) {
        this.data = data;
        this.next = null;
    }

    public String toString() {
        return data.toString();
    }
}

class NodeDetailPeminjaman {
    DetailPeminjaman data;
    NodeDetailPeminjaman next;

    public NodeDetailPeminjaman(DetailPeminjaman data) {
        this.data = data;
        this.next = null;
    }

    public String toString() {
        return data.toString();
    }
}

class SLLKaryawan {
    NodeKaryawan head;
    private int currentId;

    public SLLKaryawan() {
        this.head = null;
        this.currentId = 1; 
    }

    public void cekCurrentId() {
        if (head != null) {
            NodeKaryawan current = head;
            while (current.next != null) {
                current = current.next;
            }
            this.currentId = current.data.getId() + 1; 
        }
    }

    public void insert(String nama, String nip, String username, String password) {
        // Perbarui currentId jika head tidak kosong
        if (currentId == 1 && head != null) {
            cekCurrentId();
        }

        // Buat objek Karyawan
        Karyawan karyawan = new Karyawan(currentId++, nama, nip, username, password);
        NodeKaryawan newNode = new NodeKaryawan(karyawan);

        // Masukkan node ke dalam list
        if (head == null || head.data.getId() > newNode.data.getId()) {
            newNode.next = head;
            head = newNode;
        } else {
            NodeKaryawan current = head;
            while (current.next != null && current.next.data.getId() < newNode.data.getId()) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        System.out.println("Karyawan Berhasil Ditambahkan: " + karyawan);
    }

    public void display() {
        if (head == null) {
            System.out.println("Tidak ada mobil dalam daftar.");
            return;
        }

        NodeKaryawan temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}

class SLLMobil {
    NodeMobil head;
    private int currentId;

    // public int insert(Mobil data) {
    //     NodeMobil nn = new NodeMobil(data);
    //     NodeMobil current = head;

    //     if (current == null) {
    //         nn.next = null;
    //         head = nn;
    //     } else {
    //         while (current.next != null) {
    //             current = current.next;
    //         }
    //         current.next = nn;
    //     }
    //     return 1;
    // }

    public SLLMobil() {
        this.head = null;
        this.currentId = 1; 
    }

    public void cekCurrentId() {
        if (head != null) {
            NodeMobil current = head;
            while (current.next != null) {
                current = current.next;
            }
            this.currentId = current.data.getId() + 1; 
        }
    }

    public void insert(String nama, String jenisMobil, String nopol, int kapasitas) {
        if (currentId == 1 && head != null) {
            cekCurrentId();
        }

        Mobil mobil = new Mobil(currentId++, nama, jenisMobil, nopol, kapasitas);
        NodeMobil newNode = new NodeMobil(mobil);
        if (head == null || head.data.getId() > newNode.data.getId()) {
            newNode.next = head;
            head = newNode;
        } else {
            NodeMobil current = head;
            while (current.next != null && current.next.data.getId() < newNode.data.getId()) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        System.out.println("Mobil Berhasil Ditambahkan: " + mobil);
    }

    public void delete(int id) {
        if (head == null) {
            System.out.println("Tidak ada mobil dalam daftar.");
            return;
        }

        if (head.data.getId() == id) {
            head = head.next;
            System.out.println("Mobil dengan ID " + id + " berhasil dihapus.");
            return;
        }

        NodeMobil temp = head;
        while (temp.next != null && temp.next.data.getId() != id) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Mobil dengan ID " + id + " tidak ditemukan.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Mobil dengan ID " + id + " berhasil dihapus.");
        }
    }

    public void display() {
        if (head == null) {
            System.out.println("Tidak ada mobil dalam daftar.");
            return;
        }

        NodeMobil temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}

class SLLPeminjaman {
    NodePeminjaman head;
    private int currentId;

    public SLLPeminjaman() {
        this.head = null;
        this.currentId = 1;
    }

    public void cekCurrentId() {
        if (head != null) {
            NodePeminjaman current = head;
            while (current.next != null) {
                current = current.next;
            }
            this.currentId = current.data.getId() + 1;
        }
    }

    public void insert(NodeKaryawan karyawan, String tanggalPeminjaman, String status) {
        // Perbarui currentId jika head tidak kosong
        if (currentId == 1 && head != null) {
            cekCurrentId();
        }

        // Buat objek Peminjaman
        Peminjaman peminjaman = new Peminjaman(currentId++, karyawan, tanggalPeminjaman, status);
        NodePeminjaman newNode = new NodePeminjaman(peminjaman);

        // Masukkan node ke dalam list
        if (head == null || head.data.getId() > newNode.data.getId()) {
            newNode.next = head;
            head = newNode;
        } else {
            NodePeminjaman current = head;
            while (current.next != null && current.next.data.getId() < newNode.data.getId()) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        System.out.println("Peminjaman Berhasil Ditambahkan: " + peminjaman);
    }

    public void display() {
        if (head == null) {
            System.out.println("Tidak ada peminjaman dalam daftar.");
            return;
        }

        NodePeminjaman temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public void delete(int id) {
        if (head == null) {
            System.out.println("Tidak ada peminjaman dalam daftar.");
            return;
        }

        if (head.data.getId() == id) {
            head = head.next;
            System.out.println("Peminjaman dengan ID " + id + " berhasil dihapus.");
            return;
        }

        NodePeminjaman temp = head;
        while (temp.next != null && temp.next.data.getId() != id) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Peminjaman dengan ID " + id + " tidak ditemukan.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Peminjaman dengan ID " + id + " berhasil dihapus.");
        }
    }
}

class SLLDetailPeminjaman {
    NodeDetailPeminjaman head;

    public SLLDetailPeminjaman() {
        this.head = null;
    }

    public int insert(DetailPeminjaman data) {
        NodeDetailPeminjaman nn = new NodeDetailPeminjaman(data);
        NodeDetailPeminjaman current = head;

        if (current == null) {
            nn.next = null;
            head = nn;
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = nn;
        }
        return 1;
    }

    public void display() {
        NodeDetailPeminjaman current = head;
        while (current != null) {
            System.out.println(current.data.toString() + " ");
            current = current.next;
        }
    }
}


// kelas untuk peminjaman mobil
public class pinjammobil {
    static NodeMobil headMobil = null;
    static NodePeminjaman headPeminjamanMobil = null;
    static NodeKaryawan headKaryawan = null;

    static SLLMobil mobil = null;
    static SLLKaryawan karyawan = null;
    static SLLPeminjaman peminjaman = null;
    static SLLDetailPeminjaman detailPeminjaman = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        mobil.insert("Avanza", "Toyota Veloz", "B 123 D", 5);
        mobil.insert("Innova", "Toyota", "B 1783 DD", 8);
        mobil.insert("Sigra", "Daihatsu", "B 587 U", 5);

        boolean run = true;

        while (run) {
            System.out.println("1. Login Admin");
            System.out.println("2. Login Karyawan");
            System.out.println("3. Register Karyawan");
            System.out.println("4. Keluar");
            System.out.print("Pilih Opsi: ");
            int pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1:
                    loginadmin(scanner);
                    break;
                case 2:
                    loginkaryawan(scanner);
                    break;
                case 3:
                registerkaryawan(scanner);
                break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("Maaf Opsi Yang Dipilih Tidak Valid");
            }
        }
        scanner.close();
    }

    // fitur yang dapat dikelola admin
    static void loginadmin(Scanner scanner) {
        Admin admin = new Admin();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
            adminmenu(scanner);
        } else {
            System.out.println("Login Anda Gagal");
        }
    }

    static void adminmenu(Scanner scanner) {
        boolean adminrun = true;

        while (adminrun) {
            System.out.println("\n ------ Welcome To Menu Admin ------ ");
            System.out.println("1. Tambah Mobil");
            System.out.println("2. Hapus Mobil");
            System.out.println("3. Tampilkan Mobil");
            System.out.println("4. Histori Peminjaman Mobil");
            System.out.println("5. Log Out");
            System.out.print("Pilih Opsi: ");
            int pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1:
                    tambahmobil(scanner);
                    break;
                case 2:
                    hapusmobil(scanner);
                    break;
                case 3:
                    mobil.display();
                    break;
                case 4:
                    peminjaman.display();
                    break;
                case 5:
                    adminrun = false;
                    break;
                default:
                    System.out.println("Maaf, Opsi Yang Dipilih Tidak Valid");
            }
        }
    }

    // untuk tambah mobil
    static void tambahmobil(Scanner scanner) {
        System.out.print("Nama Mobil: ");
        String nama = scanner.nextLine();
        System.out.print("Jenis Mobil: ");
        String jenismobil = scanner.nextLine();
        System.out.print("Plat Nomor: ");
        String nopol = scanner.nextLine();
        System.out.print("Kapasitas Mobil/org: ");
        int kapasitas = scanner.nextInt();
        scanner.nextLine();

        mobil = new SLLMobil();

        mobil.insert(nama, jenismobil, nopol, kapasitas);
        // newmobil.next = headMobil;
        // headMobil = newmobil;
        System.out.println("Mobil Berhasil Ditambahkan");

    }

    // untuk hapus mobil
    static void hapusmobil(Scanner scanner) {
        System.out.print("Masukkan ID Mobil yang Ingin Dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        mobil.delete(id);
    }

    // untuk melihat histori peminjaman mobil
    // static void historipeminjamanmobil() {
    //     if (headPeminjamanMobil == null) {
    //         System.out.println("Tidak Ditemukan Histori Peminjaman");
    //         return;
    //     }
    //     NodePeminjaman temp = headPeminjamanMobil;
    //     while (temp != null) {
    //         System.out.println("Karyawan: " + temp.data.getKaryawan() + ", Mobil: " + temp.data.getDetailPeminjaman()
    //                 + ", Tanggal Peminjaman Mobil: " + temp.tanggalpeminjaman + ", Tanggal Pengembalian Mobil: "
    //                 + temp.tanggalpengembalian + ", Status: " + temp.status);
    //         temp = temp.next;
    //     }
    // }

    // fitur karyawan => registrasi awal
    static void registerkaryawan(Scanner scanner) {
        System.out.print("NIP: ");
        String NIP = scanner.nextLine();

        // validasi NIP == Angka
        if (!NIP.matches("\\d+")) {
            System.out.println("NIP Harus Berupa Angka");
            return;
        }
        // cek NIP tidak boleh sama
        if (isNIPExists(NIP)) {
            System.out.println("Maaf NIP Sudah Terdaftar");
            return;
        }

        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        // NodeKaryawan newkaryawan = new NodeKaryawan(username, password, NIP);
        // newkaryawan.next = headKaryawan;
        // headKaryawan = newkaryawan;

        karyawan.insert(username, NIP, username, password);
        System.out.println("Registrasi Berhasil");
    }

    // untuk login karyawan
    static void loginkaryawan(Scanner scanner) {
        System.out.print("NIP: ");
        String NIP = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        NodeKaryawan temp = headKaryawan;
        while (temp != null && !(temp.data.getNip().equals(NIP) && temp.data.getPassword().equals(password))) {
            temp = temp.next;
        }
        if (temp != null) {
            System.out.println("Login Berhasil");
            karyawanmenu(scanner, temp.data.getUsername());
        } else {
            System.out.println("Login Gagal");
        }
    }

    static boolean isNIPExists(String NIP) {
        NodeKaryawan temp = headKaryawan;
        while (temp != null) {
            if (temp.data.getNip().equals(NIP)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // menu karyawan
    static void karyawanmenu(Scanner scanner, String username) {
        boolean karyawanrun = true;

        while (karyawanrun) {
            System.out.println("\n ------ Welcome To Menu Karyawan ------ ");
            System.out.println("1. Lihat Daftar Mobil");
            System.out.println("2. Pinjam Mobil");
            System.out.println("3. Kembalikan Mobil");
            System.out.println("4. Log Out");
            System.out.print("Pilih Opsi: ");
            int pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1:
                    mobil.display();
                    break;
                case 2:
                    pinjammobil(scanner, username);
                    break;
                case 3:
                    kembalikanmobil(scanner, username);
                    break;
                case 4:
                    karyawanrun = false;
                    break;
                default:
                    System.out.println("Opsi Yang Dipilih Tidak Valid");
            }
        }
    }

    // untuk pinjam mobil
    static void pinjammobil(Scanner scanner, String username) {
        System.out.print("Nopol Mobil Yang Ingin Dipinjam: ");
        String plat = scanner.nextLine();
        NodeMobil temp = headMobil;

        while (temp != null && !temp.data.getNopol().equals(plat)) {
            temp = temp.next;
        }
        if (temp != null) {
            // memasukkan tanggal pinjamnya menggunakan format dd-mm-yyyy
            System.out.print("Tanggal Pinjam: ");
            String tanggalpeminjaman = scanner.nextLine();
            System.out.print("Tanggal Pengembalian: ");
            String tanggalpengembalian = scanner.nextLine();

            NodePeminjamanMobil newpeminjamanmobil = new NodePeminjamanMobil(username, namamobil, tanggalpeminjaman,
                    tanggalpengembalian, "Dipinjam");
            newpeminjamanmobil.next = headPeminjamanMobil;
            headPeminjamanMobil = newpeminjamanmobil;

            System.out.println("Mobil Berhasil Dipinjam");
        } else {
            System.out.println("Mobil Tidak Tersedia");
        }
    }

    // untuk pengembalian mobil
    static void kembalikanmobil(Scanner scanner, String username) {
        System.out.print("Nama Mobil Yang Ingin Dikembalikan: ");
        String namamobil = scanner.nextLine();
        NodePeminjaman temp = headPeminjamanMobil;

        while (temp != null && !(temp.data.getKaryawan().equals(username) && temp.namamobil.equals(namamobil)
                && temp.status.equals("Dipinjam"))) {
            temp = temp.next;
        }
        if (temp != null) {
            System.out.println("Kembali Pada: " + temp.tanggalpengembalian);
            temp.status = "Dikembalikan";
            NodeMobil mobil = headMobil;
            while (mobil != null && !mobil.nama.equals(namamobil)) {
                mobil = mobil.next;
            }
            System.out.println("Mobil Berhasil Dikembalikan");
        } else {
            System.out.println("Data Peminjaman Mobil Tidak Dapat Ditemukan");
        }
    }

}