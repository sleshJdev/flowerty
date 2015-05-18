/**
 * @author Eugene Putsykovich(slesh) May 18, 2015
 * 
 */

var s = {
	"id" : null,
	"state" : {
		"id" : 6,
		"description" : "NEW"
	},
	"cost" : 57,
	"customer" : {
		"id" : 3,
		"name" : "Sergey",
		"surname" : "Sergeew",
		"fathername" : "Sergeewitch",
		"birthday" : null,
		"email" : null,
		"address" : {
			"id" : 3,
			"town" : "London",
			"street" : "Orange",
			"house" : "12",
			"flat" : "34",
			"country" : "GB"
		},
		"phones" : [],
		"company" : {
			"id" : 1,
			"name" : "FandJ",
			"website" : "www.FandJ.com"
		},
		"contactDocument" : {
			"id" : "3",
			"name" : "Sergey",
			"surname" : "Sergeew",
			"fathername" : "Sergeewitch",
			"birthday" : null,
			"day" : 18,
			"month" : 5,
			"year" : 2015,
			"email" : null,
			"country" : "GB",
			"town" : "London",
			"street" : "Orange",
			"house" : "12",
			"flat" : "34",
			"company" : 1,
			"birthdayBefore" : null,
			"birthdayAfter" : null
		},
		"fullName" : "  "
	},
	"receiver" : {
		"id" : 3,
		"name" : "Sergey",
		"surname" : "Sergeew",
		"fathername" : "Sergeewitch",
		"birthday" : null,
		"email" : null,
		"address" : {
			"id" : 3,
			"town" : "London",
			"street" : "Orange",
			"house" : "12",
			"flat" : "34",
			"country" : "GB"
		},
		"phones" : [],
		"company" : {
			"id" : 1,
			"name" : "FandJ",
			"website" : "www.FandJ.com"
		},
		"contactDocument" : {
			"id" : "3",
			"name" : "Sergey",
			"surname" : "Sergeew",
			"fathername" : "Sergeewitch",
			"birthday" : null,
			"day" : 18,
			"month" : 5,
			"year" : 2015,
			"email" : null,
			"country" : "GB",
			"town" : "London",
			"street" : "Orange",
			"house" : "12",
			"flat" : "34",
			"company" : 1,
			"birthdayBefore" : null,
			"birthdayAfter" : null
		},
		"fullName" : "  "
	},
	"staff" : {
		"id" : 6,
		"login" : "orders_processor",
		"password" : "$2a$10$oD5eWiJRPeLAIaWxY6Caj.PK6tiaadUlCnuJdTxnqJl77VgHkgI6i",
		"role" : {
			"id" : 4,
			"name" : "ORDERS_PROCESSOR"
		},
		"contact" : {
			"id" : 7,
			"name" : "Alex",
			"surname" : "Sidorov",
			"fathername" : "Alexandrovich",
			"birthday" : "1990-12-03",
			"email" : "alex@mail.com",
			"address" : {
				"id" : 7,
				"town" : "Minsk",
				"street" : "Independence",
				"house" : "11",
				"flat" : "111",
				"country" : "Belarus"
			},
			"phones" : [],
			"company" : {
				"id" : 2,
				"name" : "FlowersForYou",
				"website" : "www.FlowersForYou.com"
			},
			"contactDocument" : {
				"id" : "7",
				"name" : "Alex",
				"surname" : "Sidorov",
				"fathername" : "Alexandrovich",
				"birthday" : "1990-12-03",
				"day" : 3,
				"month" : 12,
				"year" : 1990,
				"email" : "alex@mail.com",
				"country" : "Belarus",
				"town" : "Minsk",
				"street" : "Independence",
				"house" : "11",
				"flat" : "111",
				"company" : 2,
				"birthdayBefore" : null,
				"birthdayAfter" : null
			},
			"fullName" : "  "
		}
	},
	"manager" : {
		"id" : 5,
		"login" : "orders_manager",
		"password" : "$2a$10$eQV7Z0TJFtNWR0NKjH7BIO5vIZll.UXZYeEMrkZHkUcVQzZaA7qJu",
		"role" : {
			"id" : 3,
			"name" : "ORDERS_MANAGER"
		},
		"contact" : {
			"id" : 6,
			"name" : "Chuck",
			"surname" : "Brown",
			"fathername" : null,
			"birthday" : "1990-12-03",
			"email" : "chuck@mail.com",
			"address" : {
				"id" : 6,
				"town" : "London",
				"street" : "Green",
				"house" : "11",
				"flat" : "33",
				"country" : "GB"
			},
			"phones" : [],
			"company" : {
				"id" : 1,
				"name" : "FandJ",
				"website" : "www.FandJ.com"
			},
			"contactDocument" : {
				"id" : "6",
				"name" : "Chuck",
				"surname" : "Brown",
				"fathername" : null,
				"birthday" : "1990-12-03",
				"day" : 3,
				"month" : 12,
				"year" : 1990,
				"email" : "chuck@mail.com",
				"country" : "GB",
				"town" : "London",
				"street" : "Green",
				"house" : "11",
				"flat" : "33",
				"company" : 1,
				"birthdayBefore" : null,
				"birthdayAfter" : null
			},
			"fullName" : "null  "
		}
	},
	"delivery" : {
		"id" : 1,
		"login" : "test",
		"password" : "$2a$10$ZWwh6S.iW5Sjeo2mklifkegHdSDOpmxpAw5oHDRTEMWgHLS.bILny",
		"role" : {
			"id" : 2,
			"name" : "DELIVERY_MANAGER"
		},
		"contact" : {
			"id" : 2,
			"name" : "Ivan",
			"surname" : "Ivanov",
			"fathername" : "Ivanovich",
			"birthday" : "1990-10-04",
			"email" : "ivanov@mail.com",
			"address" : {
				"id" : 2,
				"town" : "Moscow",
				"street" : "Lermontova",
				"house" : "1",
				"flat" : "2",
				"country" : "Russia"
			},
			"phones" : [ {
				"id" : 3,
				"comment" : "Comment3",
				"country" : "56",
				"number" : "90123",
				"operator" : "78",
				"type" : "CELL"
			}, {
				"id" : 4,
				"comment" : "Comment4",
				"country" : "34",
				"number" : "78901",
				"operator" : "56",
				"type" : "HOME"
			}, {
				"id" : 6,
				"comment" : "Comment6",
				"country" : "34",
				"number" : "78901",
				"operator" : "56",
				"type" : "HOME"
			} ],
			"company" : {
				"id" : 1,
				"name" : "FandJ",
				"website" : "www.FandJ.com"
			},
			"contactDocument" : {
				"id" : "2",
				"name" : "Ivan",
				"surname" : "Ivanov",
				"fathername" : "Ivanovich",
				"birthday" : "1990-10-04",
				"day" : 4,
				"month" : 10,
				"year" : 1990,
				"email" : "ivanov@mail.com",
				"country" : "Russia",
				"town" : "Moscow",
				"street" : "Lermontova",
				"house" : "1",
				"flat" : "2",
				"company" : 1,
				"birthdayBefore" : null,
				"birthdayAfter" : null
			},
			"fullName" : "  "
		}
	},
	"description" : "bbbbb",
	"deliveryDate" : "2015-05-30",
	"items" : [ {
		"goods" : {
			"id" : 5,
			"cost" : 19,
			"remain" : 9,
			"company" : {
				"id" : 1,
				"name" : "FandJ",
				"website" : "www.FandJ.com"
			},
			"flower" : {
				"id" : 4,
				"name" : "Camomile"
			},
			"image" : "flower-iris.jpg",
			"count" : 3
		},
		"quantity" : 3
	} ],
	"address" : {
		"town" : "qwe",
		"street" : "qwe",
		"house" : "qweqwe",
		"flat" : "qweqew",
		"country" : "qweqwe"
	},
	"orderDocument" : null
}