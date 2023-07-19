/*
 Navicat Premium Data Transfer

 Source Server         : MongoDB
 Source Server Type    : MongoDB
 Source Server Version : 50004
 Source Host           : localhost:27017
 Source Schema         : swa_project

 Target Server Type    : MongoDB
 Target Server Version : 50004
 File Encoding         : 65001

 Date: 20/07/2023 00:52:58
*/


// ----------------------------
// Collection structure for school
// ----------------------------
db.getCollection("school").drop();
db.createCollection("school");

// ----------------------------
// Documents of school
// ----------------------------
db.getCollection("school").insert([ {
    _id: ObjectId("64b7f0cd3289580088833157"),
    name: "School 1",
    address: "Address 1",
    email: "email1@gmail.com",
    phone: "0912343543",
    _class: "school.entity.School"
} ]);
db.getCollection("school").insert([ {
    _id: "6fd9adc4-86da-4eb7-a8cc-07ed073c02d3",
    name: "School 1",
    address: "Address 1",
    email: "email1@gmail.com",
    phone: "0912343543",
    _class: "school.entity.School"
} ]);

// ----------------------------
// Collection structure for teacher
// ----------------------------
db.getCollection("teacher").drop();
db.createCollection("teacher");

// ----------------------------
// Documents of teacher
// ----------------------------
db.getCollection("teacher").insert([ {
    _id: "7d89ccff-a125-46e9-a0fb-4b9d9d566e68",
    "first_name": "first_name_1",
    "last_name": "last_name_1",
    email: "teacher1@gmail.com",
    phone: "0912354354",
    school: "School_1",
    "teaching_class": {
        year: NumberInt("2023"),
        group: "A"
    },
    _class: "teacher.entity.Teacher"
} ]);
db.getCollection("teacher").insert([ {
    _id: "30373207-5a57-4a53-a593-76245f4f44e1",
    "first_name": "first_name_1",
    "last_name": "last_name_1",
    email: "teacher1@gmail.com",
    phone: "0912354354",
    school: "School_1",
    "teaching_class": {
        year: NumberInt("2023"),
        group: "A"
    },
    _class: "teacher.entity.Teacher"
} ]);
db.getCollection("teacher").insert([ {
    _id: "34120da0-8185-400c-961b-a19b650d589e",
    "first_name": "first_name_1",
    "last_name": "last_name_1",
    email: "teacher1@gmail.com",
    phone: "0912354354",
    school: "School_1",
    "teaching_class": {
        year: NumberInt("2023"),
        group: "A"
    },
    _class: "teacher.entity.Teacher"
} ]);
db.getCollection("teacher").insert([ {
    _id: "8ac3b9f2-21a9-4c73-826c-6d751a22b1b6",
    "first_name": "first_name_1",
    "last_name": "last_name_1",
    email: "teacher1@gmail.com",
    phone: "0912354354",
    school: "School_1",
    "teaching_class": {
        year: NumberInt("2023"),
        group: "A"
    },
    _class: "teacher.entity.Teacher"
} ]);
db.getCollection("teacher").insert([ {
    _id: "f1ecbd9d-53c6-4c38-a57a-e2204e30165c",
    "first_name": "first_name_1",
    "last_name": "last_name_1",
    email: "teacher1@gmail.com",
    phone: "0912354354",
    school: "School_1",
    "teaching_class": {
        year: NumberInt("2023"),
        group: "A"
    },
    _class: "teacher.entity.Teacher"
} ]);
db.getCollection("teacher").insert([ {
    _id: "98e21742-cf8e-40a8-99ca-d230e8403fa6",
    "first_name": "first_name_1",
    "last_name": "last_name_1",
    email: "teacher1@gmail.com",
    phone: "0912354354",
    school: "School_1",
    "teaching_class": {
        year: NumberInt("2023"),
        group: "A"
    },
    _class: "teacher.entity.Teacher"
} ]);

// ----------------------------
// Collection structure for user
// ----------------------------
db.getCollection("user").drop();
db.createCollection("user");

// ----------------------------
// Documents of user
// ----------------------------
db.getCollection("user").insert([ {
    _id: ObjectId("64b8077d2224c72a4fb05da3"),
    username: "teacher1@gmail.com",
    password: "$2a$10$0rEP7jJdNcmrHfXfCo/7Hu3QK/OddyhBZC3jOX5so7n2szbg2CZO6",
    role: "Teacher",
    _class: "user.entity.User"
} ]);
db.getCollection("user").insert([ {
    _id: "623bd90624c7b71478064326",
    username: "admin",
    password: "$2a$10$r81oXBSfENjvFPu.qsj5LOQ91p7sBjG8F2zZEevop1JcAWcftvsUi",
    role: "Admin"
} ]);
db.getCollection("user").insert([ {
    _id: ObjectId("64b80c8016cb606f1b1fea28"),
    username: "teacher1@gmail.com",
    password: "$2a$10$9UasvX2T.FB2u/6VeKM1kucLuPgLsFVvrpvvA5fhxjgBM2qpR6oye",
    role: "Teacher",
    _class: "user.entity.User"
} ]);
db.getCollection("user").insert([ {
    _id: ObjectId("64b80ccf16cb606f1b1fea29"),
    username: "teacher1@gmail.com",
    password: "$2a$10$cd6s24ycOaz6JWecZ79yOOWEWP.7HKlx6zwzsTTTg7V00yjg6T8Zq",
    role: "Teacher",
    _class: "user.entity.User"
} ]);
db.getCollection("user").insert([ {
    _id: ObjectId("64b81780ebbf2340391b2904"),
    username: "teacher1@gmail.com",
    password: "$2a$10$ucOin9qwCOVpTEV6tTuSoeOdqvqtJ2dh3x1Lqtk6yVvzXKOYbBsKi",
    role: "Teacher",
    _class: "user.entity.User"
} ]);
db.getCollection("user").insert([ {
    _id: ObjectId("64b817f5ebbf2340391b2905"),
    username: "teacher1@gmail.com",
    password: "$2a$10$H6CHorc5u4j/QLtS7Bs7COxDqNL1qVGGy0niJo09bqkvj6svopvTK",
    role: "Teacher",
    _class: "user.entity.User"
} ]);
db.getCollection("user").insert([ {
    _id: ObjectId("64b819846599b66128dfc71a"),
    username: "teacher1@gmail.com",
    password: "$2a$10$8cepw7IxPe8iKxHdFRPuN.L3a9osOibyuSe9R1N7gnnqetVkLdeQi",
    role: "Teacher",
    _class: "user.entity.User"
} ]);
db.getCollection("user").insert([ {
    _id: ObjectId("64b819856599b66128dfc71b"),
    username: "teacher1@gmail.com",
    password: "$2a$10$VYNckdHfMoAvLLADnxcGWerrvP2hy45.SY.7ryzsz9iWTMx8pH6Ha",
    role: "Teacher",
    _class: "user.entity.User"
} ]);
db.getCollection("user").insert([ {
    _id: ObjectId("64b819c86599b66128dfc71c"),
    username: "teacher1@gmail.com",
    password: "$2a$10$1vNite0IC1yCVcpn5IKXoeAJ7x9mRbXSdN105uQxz.8hc/NkjG5mS",
    role: "Teacher",
    _class: "user.entity.User"
} ]);
db.getCollection("user").insert([ {
    _id: "d4970c99-3492-46de-9123-bc14565ac40e",
    username: "admin@gmail.com",
    password: "$2a$10$3LMVJKfEaZdSyBnb93PDMuK5sYTKrGSCJY/pK7rp7iYzOXDi5lcWy",
    role: "Admin",
    _class: "user.entity.User"
} ]);
