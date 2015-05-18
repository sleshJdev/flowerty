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
	"cost" : 132.1199951171875,
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
		"fullName" : "  ",
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
		}
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
		"fullName" : "  ",
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
		}
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
			"fullName" : "  ",
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
			}
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
			"fullName" : "null  ",
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
			}
		}
	},
	"delivery" : {
		"id" : 4,
		"login" : "delivery_manager",
		"password" : "$2a$10$Okl4EXDsJLi8grBfGVr0aeHz8jsO3VnQM.MJVr/2PMROPZOaTBnQG",
		"role" : {
			"id" : 2,
			"name" : "DELIVERY_MANAGER"
		},
		"contact" : {
			"id" : 5,
			"name" : "John",
			"surname" : "Brown",
			"fathername" : null,
			"birthday" : "1990-12-03",
			"email" : "john@mail.com",
			"address" : {
				"id" : 5,
				"town" : "Moscow",
				"street" : "Pushkina",
				"house" : "10",
				"flat" : "20",
				"country" : "Russia"
			},
			"phones" : [],
			"company" : {
				"id" : 3,
				"name" : "GoodPresents",
				"website" : "www.GoodPresents.com"
			},
			"fullName" : "null  ",
			"contactDocument" : {
				"id" : "5",
				"name" : "John",
				"surname" : "Brown",
				"fathername" : null,
				"birthday" : "1990-12-03",
				"day" : 3,
				"month" : 12,
				"year" : 1990,
				"email" : "john@mail.com",
				"country" : "Russia",
				"town" : "Moscow",
				"street" : "Pushkina",
				"house" : "10",
				"flat" : "20",
				"company" : 3,
				"birthdayBefore" : null,
				"birthdayAfter" : null
			}
		}
	},
	"description" : null,
	"items" : [ {
		"goods" : {
			"id" : 2,
			"cost" : 132.1199951171875,
			"remain" : 109,
			"company" : {
				"id" : 1,
				"name" : "FandJ",
				"website" : "www.FandJ.com"
			},
			"flower" : {
				"id" : 2,
				"name" : "White Rose"
			},
			"image" : null,
			"count" : 1,
			"$$hashKey" : "object:55"
		},
		"quantity" : 1,
		"$$hashKey" : "object:77"
	} ],
	"deliveryDate" : "2015-05-23",
	"address" : {
		"town" : "a",
		"street" : "a",
		"house" : "a",
		"country" : "a",
		"flat" : "a"
	},
	"orderDocument" : null
}