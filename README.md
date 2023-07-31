# Point Of Sales API
API kasir sederhana dengan fitur transaksi yang menyimpan data kasir, serta customer. Menggunakan database postgreSQL, Springboot Framework, serta RestfulAPI dalam penggunaannya.
Endpoints

Koneksi postgreSQL :

    application.properties:
    {
        spring.datasource.username={username-datasource}
        spring.datasource.password={password-datasource}
        spring.datasource.url=jdbc:postgresql://localhost:5432/{database}
    }

### Berikut adalah daftar endpoint yang tersedia:
### 1. /customers

Endpoint ini digunakan untuk mengelola data pelanggan.

  GET /customers
  
    Deskripsi: Mendapatkan daftar pelanggan.
    Contoh Permintaan: GET /customers
    Contoh Input:
    json
    
    [
      {
          "id": "CTM01",
          "name": "customer",
          "phone": "098765"
      },
      {
          "id": "CTM11",
          "name": "Customer 2",
          "phone": "0865533"
      }
    ]

 GET /customers/{phone}

    Deskripsi: Mendapatkan data pelanggan berdasarkan ID.
    Contoh Permintaan: GET /customers/098765
    Contoh Respons:
    json

    {
        "id": "CTM01",
        "name": "customer",
        "phone": "098765"
    }

 POST /customers

    Deskripsi: Membuat pelanggan baru.
    Contoh Permintaan:
    json
    
    {
      "name": "customer",
      "phone": "098765"
    }

    Contoh Response:
    {
        "id": "CTM01",
        "name": "customer",
        "phone": "098765"
    }
    
 PUT /customers/{id}

    Deskripsi: Memperbarui data pelanggan berdasarkan ID.
    Contoh Permintaan:
    json
    {
      "id": "CTM01",
      "name": "Customer 01",
      "phone": "08765444"
    }

### 2. /employees

Endpoint ini digunakan untuk mengelola data kasir.

  GET /employees

    Deskripsi: Mendapatkan daftar kasir.
    Contoh Permintaan: GET /employees
    Contoh Respons:
    json
    [
      {
          "name": "karyawan",
          "email": "karyawan@mail.com"
      },
      {
          "name": "karyawan 02",
          "email": "karyawan2@mail.com"
      }
    ]
    
  GET /employees

    Deskripsi: Mendapatkan daftar kasir.
    Contoh Permintaan: GET /employees?email=karyawan@mail.com
    Contoh Respons:
    json
  
    {
        "name": "karyawan",
        "email": "karyawan@mail.com"
    },
      
 POST /employees

    Deskripsi: Membuat kasir baru.
    Contoh Permintaan:
    json
    {
      "name": "karyawan 02",
      "email": "karyawan2@mail.com"
    }

 PUT /employees/{email}

    Deskripsi: Memperbarui data kasir berdasarkan email.
    Contoh Permintaan:
    json

    {
      "name": "karyawan 02",
      "email": "karyawan2@mail.com"
    }

### 3./products

Endpoint ini digunakan untuk mengelola data produk yang tersedia di toko.

  GET /products
  
    Deskripsi: Mendapatkan daftar produk yang tersedia.
    Contoh Permintaan: GET /products
    Contoh Respons:
    json

    [
      {
          "codeProduct": "BAJ1",
          "productName": "Baju",
          "price": 80000,
          "category": "pakaian"
      },
      {
          "codeProduct": "CEL2",
          "productName": "Celana",
          "price": 10000,
          "category": "pakaian"
      }
    ]

GET /products

    Deskripsi: Mendapatkan data produk berdasarkan code produk.
    Contoh Permintaan: GET /products?code=BAJ1
    Contoh Respons:
    json

    {
      "codeProduct": "BAJ1",
      "productName": "Baju",
      "price": 80000,
      "category": "pakaian"
    }
    
POST /products

    Deskripsi: Menambahkan produk baru ke daftar produk yang tersedia.
    Contoh Permintaan:
    json
    
    {
      "productName": "Celana",
      "price": 10000,
      "category": "Pakaian"
    }  

Contoh Respons:
    
    json

    {
        "codeProduct": "CEL2",
        "productName": "Celana",
        "price": 10000,
        "category": "pakaian"
    }

PUT /products

    Deskripsi: Memperbarui data produk berdasarkan ID.
    Contoh Permintaan:
    json

    {
        "codeProduct": "BAJ1",
        "productName": "Baju Kecil",
        "price": 90000,
        "category": "Pakaian"
    }

Contoh Respons:
  
    json

    {
        "codeProduct": "BAJ1",
        "productName": "Baju Kecil",
        "price": 90000,
        "category": "Pakaian"
    }

DELETE /products/{code}

    Deskripsi: Menghapus produk dari daftar produk berdasarkan ID.
    Contoh Permintaan: DELETE /products/BAJ1
    Contoh Respons: Success
    
### 4. /orders

Endpoint ini digunakan untuk mengelola transaksi (pesanan).

    GET /orders
        Deskripsi: Mendapatkan daftar pesanan.
        Contoh Permintaan: GET /orders
        Contoh Respons:
        json

    [
      {
          "invoice": "INV/MONDAY/1",
          "orderDate": "2023-07-31",
          "detailResponse": [
              {
                  "codeProduct": "BAJ1",
                  "productName": "Baju",
                  "price": 80000,
                  "subTotal": 320000
              }
          ],
          "customerName": "customer",
          "employeeName": "karyawan"
      },
      {
          "invoice": "INV/MONDAY/2",
          "orderDate": "2023-07-31",
          "detailResponse": [
              {
                  "codeProduct": "BAJ1",
                  "productName": "Baju",
                  "price": 80000,
                  "subTotal": 320000
              },
              {
                  "codeProduct": "CEL2",
                  "productName": "Celana",
                  "price": 10000,
                  "subTotal": 50000
              }
          ],
          "customerName": "customer",
          "employeeName": "karyawan"
      }
    ]

 GET /orders

    Deskripsi: Mendapatkan data pesanan berdasarkan ID.
    Contoh Permintaan: GET /orders?name=INV/MONDAY/2
    Contoh Respons:
    json

    {
    "invoice": "INV/MONDAY/2",
    "orderDate": "2023-07-31",
    "detailResponse": [
        {
            "codeProduct": "BAJ1",
            "productName": "Baju",
            "price": 80000,
            "subTotal": 320000
        },
        {
            "codeProduct": "CEL2",
            "productName": "Celana",
            "price": 10000,
            "subTotal": 50000
        }
    ],
    "customerName": "customer",
    "employeeName": "karyawan"
    }

 POST /orders

    Deskripsi: Membuat pesanan baru.
    Contoh Permintaan:
    json
    
    {
      "orderDetailRequests": [
        {
          "codeProduct": "BAJ1",
          "quantity": 4
        },  
        {
          "codeProduct": "CEL2",
          "quantity": 5
        }
      ],
      "customerPhone": "098765",
      "employeeEmail": "karyawan@mail.com"
    }

  Contoh Respons:

    {
      "invoice": "INV/MONDAY/2",
      "orderDate": "2023-07-31",
      "detailResponse": [
          {
              "codeProduct": "BAJ1",
              "productName": "Baju",
              "price": 80000,
              "subTotal": 320000
          },
          {
              "codeProduct": "CEL2",
              "productName": "Celana",
              "price": 10000,
              "subTotal": 50000
          }
      ],
      "customerName": "customer",
      "employeeName": "karyawan"
    }
