'use strict';
var initialXlatTables = {
    'en': {
        'CONTACTS': 'Contacts',
        'ORDERS' : 'Orders',
        'USERS' : 'Users',
        'ADD_GOODS' : 'Add goods',
        'YOUR_ROLE' : 'your role is',
        'LOG_IN' : 'Log in',
        'LOGIN' : 'Login',
        'LOGOUT' : 'Log out',
        'ABOUT' : 'About',
        'HELP' : 'Help',
        'UNABLE_LOGIN' : 'Unable to log in.',
        'WRONG_PASS' : 'Please make sure you have entered your login and password correctly.',
        'WRONG_PASS_CAPS' : 'Is the Caps Lock safely turned off?',
        'WRONG_PASS_LANG' : 'Maybe you are using the wrong input language? (e.g. German vs. English)',
        'WRONG_PASS_EDITOR' : 'Try typing your password in a text editor and pasting it into the "Password" field.',
        'PASSWORD' : 'Password',
        'REMEMBER_ME' : 'remember me',
        'NOT_REG_YET' : 'Not registered yet?',
        'SIGN_UP' : 'Sign up!',
        'NAME' : 'Name',
        'SURNAME' : 'Surname',
        'FATHERNAME' : 'Fathername',
        'BIRTHDAY' : 'Birthday',
        'TOWN' : 'Town',
        'STREET' : 'Street',
        'FLAT' : 'Flat',
        'HOUSE' : 'House',
        'COUNTRY' : 'Country',
        'EMAIL' : 'Email',
        'ENGLISH' : 'English',
        'RUSSIAN' : 'Русский',
        'FULL_NAME' : 'Full name',
        'ADDRESS' : 'Address',
        'CITY_CODE' : 'City code:',
        'OPERATOR_CODE' : 'Provider code:',
        'NUMBER' : 'Number:',
        'TYPE' : 'Type',
        'COMMENT' : 'Comment:',
        'CLOSE' : 'Close',
        'SAVE' : 'Save',
        'PHONES' : 'Phones',
        'SEND_EMAIL' : 'Send email',
        'TO' : 'To',
        'SUBJECT' : 'Subject',
        'TEMPLATE' : 'Template',
        'ATTACHMENTS' : 'Attachments',
        'MESSAGE' : 'Message',
        'COST' : 'Cost',
        'FLOWER' : 'Flower',
        'REMAIN' : 'Remain',
        'TYPE_FLOWER_NAME' : 'Type flower name here',
        'ON_WAREHOUSE' : 'On warehouse',
        'PICTURE' : 'Picture',
        'ADD' : 'Add',
        'YOUR_ORDER' : 'Your order',
        'ITEMS_COUNT' : 'Items count:',
        'FULL_COST' : 'Full cost:',
        'FORM_ORDER' : 'Form the order',
        'MAKE_ORDER' : 'Make an order!',
        'ADD_BASKET' : 'Add to basket',
        'REMOVE_BASKET' : 'Remove from basket',
        'PHONE_LIST_EMPTY' : 'Phone list is empty.',
        'CANT_LEAVE_EMPTY' : 'You can\'t leave this empty.',
        'RESET_PASSWORD' : 'Reset the password',
        'CREATE_PASS' : 'Create a password',
        'CONFIRM_PASS' : 'Confirm your password',
        'PASSWORDS_DONT_MATCH' : 'The passwords don\'t match',
        'CANCEL' : 'Cancel',
        'ROLE' : 'Role',
        'ROLES' : 'Roles',
        'CONTACT_AND_INFO' : 'CONTACT AND BASICS INFORMATION',
        'HOMETOWN' : 'Hometown',
        'FRENCH' : 'Français',
        'COUNT' : 'Count',
        'NOT_AVAILABLE' : 'Not available at warehouse now',
        'CHECKOUT' : 'Checkout',
        'EDIT_ORDER' : 'Edit order',
        'STATE' : 'State',
        'CUSTOMER' : 'Customer',
        'START_TYPING' : 'start typing',
        'RECEIVER' : 'Receiver',
        'DESCRIPTION' : 'Description',
        'DELIVERY_DATE' : 'Delivery date',
        'SELECT_DATE' : 'select date',
        'STAFF' : 'Staff',
        'ORDERS_MANAGER' : 'Orders manager',
        'ORDERS_PROCESSOR' : 'Orders processor',
        'DELIVERY_MANAGER' : 'Delivery manager',
        'SEARCH_ORDER' : 'Search order',
        'SURNAME_OF_CUSTOMER' : 'Surname of customer',
        'SURNAME_OF_RECEIVER' : 'Surname of receiver',
        'DELIVERY_DATE_AFTER' : 'Delivery date after',
        'CHOOSE_DATE' : 'Choose date',
        'DELIVERY_DATE_BEFORE' : 'Delivery date before',
        'SEARCH' : 'Search',
        'HISTORY_OF_THE_ORDER' : 'History of the order',
        'DATE' : 'Date',
        'NEW_STATUS' : 'New status',
        'WHO_CHANGED' : 'Who changed',
        'CHANGING_STATUS' : 'Changing status',
        'CHANGE' : 'Change',
        'YOUR_COMMENT' : 'your comment',
        'Add Contact' : 'Add contact',
        'Edit Contact' : 'Edit contact',
        'Add new contact' : 'Add new contact',
        'Save contact' : 'Save contact',
        'Add phone' : 'Add phone',
        'Search Contact' : 'Search contact',
        'Search contact' : 'Search',
        'BIRTHDAY_BEFORE' : 'Birthday before',
        'BIRTHDAY_AFTER' : 'Birthday after',
        'EXACT_DATE' : 'Exact date',
        'YEAR' : 'Year',
        'MONTH' : 'Month',
        'DAY' : 'Day',
        'Orders manager' : 'orders manager',
        'Orders processor' : 'orders processor',
        'Delivery manager' : 'delivery manager',
        'Admin' : 'admin',
        'Role orders manager' : 'orders manager',
        'Role orders processor' : 'orders processor',
        'Role delivery manager' : 'delivery manager',
        'Role admin' : 'admin',
        'Add User' : 'Add user',
        'Add' : 'Add'
    },
    'ru': {
        'CONTACTS': 'Контакты',
        'ORDERS' : 'Заказы',
        'USERS' : 'Пользователи',
        'ADD_GOODS' : 'Добавить товар',
        'YOUR_ROLE' : 'Ваша роль',
        'LOG_IN' : 'Войти',
        'LOGIN' : 'Логин',
        'LOGOUT' : 'Выйти',
        'ABOUT' : 'О сайте',
        'HELP' : 'Справка',
        'UNABLE_LOGIN' : 'Unable to log in.',
        'WRONG_PASS' : 'Please check that you have entered your login and password correctly.',
        'WRONG_PASS_CAPS' : 'Is the Caps Lock safely turned off?',
        'WRONG_PASS_LANG' : 'Maybe you are using the wrong input language? (e.g. German vs. English)',
        'WRONG_PASS_EDITOR' : 'Try typing your password in a text editor and pasting it into the "Password" field.',
        'PASSWORD' : 'Пароль',
        'REMEMBER_ME' : 'запомнить меня',
        'NOT_REG_YET' : 'Еще не зарегистрированы?',
        'SIGN_UP' : 'Зарегистрироваться!',
        'NAME' : 'Имя',
        'SURNAME' : 'Фамилия',
        'FATHERNAME' : 'Отчество',
        'BIRTHDAY' : 'День рождения',
        'TOWN' : 'Город',
        'STREET' : 'Улица',
        'FLAT' : 'Квартира',
        'HOUSE' : 'Дом',
        'COUNTRY' : 'Страна',
        'EMAIL' : 'Email',
        'ENGLISH' : 'English',
        'RUSSIAN' : 'Русский',
        'FULL_NAME' : 'Полное имя',
        'ADDRESS' : 'Адрес',
        'CITY_CODE' : 'Код города',
        'ADD' : 'Добавить',
        'YOUR_ORDER' : 'Ваш заказ',
        'OPERATOR_CODE' : 'Код оператора:',
        'NUMBER' : 'Номер:',
        'TYPE' : 'Тип',
        'COMMENT' : 'Комментарий:',
        'CLOSE' : 'Закрыть',
        'SAVE' : 'Сохранить',
        'PHONES' : 'Телефоны',
        'SEND_EMAIL' : 'Отправить письмо',
        'TO' : 'Кому',
        'SUBJECT' : 'Тема',
        'TEMPLATE' : 'Шаблон',
        'ATTACHMENTS' : 'Присоединенные файлы',
        'MESSAGE' : 'Сообщение',
        'COST' : 'Стоимость',
        'FLOWER' : 'Цветок',
        'REMAIN' : 'Остаток',
        'TYPE_FLOWER_NAME' : 'Введите название цветка здесь.',
        'ON_WAREHOUSE' : 'На складе',
        'PICTURE' : 'Изображение',
        'ITEMS_COUNT' : 'Число единиц:',
        'FULL_COST' : 'Полная стоимость:',
        'FORM_ORDER' : 'Оформить заказ',
        'MAKE_ORDER' : 'Сделайте заказ!',
        'ADD_BASKET' : 'Добавить в корзину',
        'REMOVE_BASKET' : 'Удалить из корзины',
        'PHONE_LIST_EMPTY' : 'Список телефонов пуст.',
        'CANT_LEAVE_EMPTY' : 'Пожалуйста, заполните это поле.',
        'RESET_PASSWORD' : 'Сбросить пароль',
        'CREATE_PASS' : 'Установить пароль',
        'CONFIRM_PASS' : 'Подтвердите пароль',
        'PASSWORDS_DONT_MATCH' : 'Пароли не совпадают',
        'CANCEL' : 'Отмена',
        'ROLE': 'Роль',
        'ROLES' : 'Роли',
        'CONTACT_AND_INFO' : 'КОНТАКТ И ОСНОВНАЯ ИНФОРМАЦИЯ',
        'HOMETOWN' : 'Родной город',
        'FRENCH' : 'Français',
        'COUNT' : 'Количество',
        'NOT_AVAILABLE' : 'В данный момент отсутствует на складе',
        'CHECKOUT' : 'Оформить заказ',
        'EDIT_ORDER' : 'Редактировать заказ',
        'STATE' : 'Статус',
        'CUSTOMER' : 'Заказчик',
        'START_TYPING' : 'начните печатать',
        'RECEIVER' : 'Получатель',
        'DESCRIPTION' : 'Описание',
        'DELIVERY_DATE' : 'Дата доставки',
        'SELECT_DATE' : 'выберите дату',
        'STAFF' : 'Персонал',
        'ORDERS_MANAGER' : 'Менеджер по заказам',
        'ORDERS_PROCESSOR' : 'Специалист по обработке',
        'DELIVERY_MANAGER' : 'Менеджер по доставке',
        'SEARCH_ORDER' : 'Поиск заказа',
        'SURNAME_OF_CUSTOMER' : 'Фамилия заказчика',
        'SURNAME_OF_RECEIVER' : 'Фамилия получателя',
        'DELIVERY_DATE_AFTER' : 'Дата доставки после',
        'CHOOSE_DATE' : 'Выберите дату',
        'DELIVERY_DATE_BEFORE' : 'Дата доставки до',
        'SEARCH' : 'Искать',
        'HISTORY_OF_THE_ORDER' : 'История заказа',
        'DATE' : 'Дата',
        'NEW_STATUS' : 'Новый статус',
        'WHO_CHANGED' : 'Кто изменил',
        'CHANGING_STATUS' : 'Изменение статуса',
        'CHANGE' : 'Изменить',
        'YOUR_COMMENT' : 'ваш комментарий',
        'Add Contact' : 'Создать контакт',
        'Edit Contact' : 'Редактировать контакт',
        'Add new contact' : 'Создать',
        'Save contact' : 'Сохранить',
        'Add phone' : 'Добавить телефон',
        'Search Contact' : 'Поиск контакта',
        'Search contact' : 'Искать',
        'BIRTHDAY_BEFORE' : 'Дата рождения до',
        'BIRTHDAY_AFTER' : 'Дата рождения после',
        'EXACT_DATE' : 'Точная дата',
        'YEAR' : 'Год',
        'MONTH' : 'Месяц',
        'DAY' : 'День',
        'Orders manager' : 'менеджер по заказам',
        'Orders processor' : 'специалист по обработке',
        'Delivery manager' : 'менеджер по доставке',
        'Admin' : 'админ',
        'Role orders manager' : 'менеджер по заказам',
        'Role orders processor' : 'специалист по обработке',
        'Role delivery manager' : 'менеджер по доставке',
        'Role admin' : 'админ',
        'Add User' : 'Создать пользователя',
        'Add' : 'Добавить'
    },
    'fr': {
        'CONTACTS': 'Contact',
        'ORDERS' : 'Ordres',
        'USERS' : 'Utilisateurs',
        'ADD_GOODS' : 'Ajouter une marchandise',
        'YOUR_ROLE' : 'Votre role',
        'LOG_IN' : 'Se connecter',
        'LOGIN' : 'Login',
        'LOGOUT' : 'Se déconnecter',
        'ABOUT' : 'À propos du site',
        'HELP' : 'Aide',
        'UNABLE_LOGIN' : 'Impossible de vous connecter.',
        'WRONG_PASS' : 'Veuillez verifier le login et le mot de passe que vous avez entrés.',
        'WRONG_PASS_CAPS' : 'Est-ce que le Caps Lock est éteint?',
        'WRONG_PASS_LANG' : 'Peut-être que vous utilisez un faux une langue ? (par example, l\'Anglais à lieu du Français)',
        'WRONG_PASS_EDITOR' : 'Essayez de taper votre mot de passe dans un editeur texto et le copier dans le champ "Mot de passe".',
        'PASSWORD' : 'Mot de passe',
        'REMEMBER_ME' : 'mémoriser moi',
        'NOT_REG_YET' : 'Pas encore enrégistrés?',
        'SIGN_UP' : 'S\'énregistrer!',
        'NAME' : 'Le rénom',
        'SURNAME' : 'Le nom',
        'FATHERNAME' : 'Le nom du pére',
        'BIRTHDAY' : 'L\'anniversaire',
        'TOWN' : 'La ville',
        'STREET' : 'La rue',
        'FLAT' : 'L\'appartement',
        'HOUSE' : 'La maison',
        'COUNTRY' : 'Le pays',
        'EMAIL' : 'Le Email',
        'ENGLISH' : 'English',
        'RUSSIAN' : 'Русский',
        'FULL_NAME' : 'Le nom complet',
        'ADDRESS' : 'L\'addresse',
        'CITY_CODE' : 'La code de ville',
        'ADD' : 'Ajouter',
        'YOUR_ORDER' : 'Votre ordre',
        'OPERATOR_CODE' : 'La code de provoyeur:',
        'NUMBER' : 'Le numero:',
        'TYPE' : 'Le type',
        'COMMENT' : 'Le commentaire:',
        'CLOSE' : 'Fermer',
        'SAVE' : 'Sauver',
        'PHONES' : 'Les numeros de télephone',
        'SEND_EMAIL' : 'Envoyer le message',
        'TO' : 'À',
        'SUBJECT' : 'Le sujet',
        'TEMPLATE' : 'le modèle',
        'ATTACHMENTS' : 'Les fichiers ajoutés',
        'MESSAGE' : 'Le message',
        'COST' : 'Le prix',
        'FLOWER' : 'La fleur',
        'REMAIN' : 'Le reste',
        'TYPE_FLOWER_NAME' : 'Entrez le nom du fleur là.',
        'ON_WAREHOUSE' : 'Dans l\'entrepôt',
        'PICTURE' : 'L\'image',
        'ITEMS_COUNT' : 'Le nombres des entités:',
        'FULL_COST' : 'Le prix total:',
        'FORM_ORDER' : 'Former l\'ordre',
        'MAKE_ORDER' : 'Fâites une ordre!',
        'ADD_BASKET' : 'Ajouter dans la corbeille',
        'REMOVE_BASKET' : 'Supprimer du corbeille',
        'PHONE_LIST_EMPTY' : 'Le liste des télephones est vide.',
        'CANT_LEAVE_EMPTY' : 'Veuillez enfiller ce champ.',
        'RESET_PASSWORD' : 'Remettre le mot de passe',
        'CREATE_PASS' : 'Créer un mot de passe',
        'CONFIRM_PASS' : 'Confirmez le mot de passe',
        'PASSWORDS_DONT_MATCH' : 'Les mot de passe n\'égalèrent pas',
        'CANCEL' : 'Annuler',
        'ROLE': 'La role',
        'ROLES' : 'Les roles',
        'CONTACT_AND_INFO' : 'Le contact et l\'information principale',
        'HOMETOWN' : 'La ville matérnèle',
        'FRENCH' : 'Français',
        'COUNT' : 'Nombre',
        'NOT_AVAILABLE' : 'Actuellement en rupture de stock',
        'CHECKOUT' : 'Сaisse',
        'EDIT_ORDER' : 'Modifier l\'ordre',
        'STATE' : 'Statut',
        'CUSTOMER' : 'Le client',
        'START_TYPING' : 'commencez à taper',
        'RECEIVER' : 'Le destinataire',
        'DESCRIPTION' : 'La description',
        'DELIVERY_DATE' : 'Date de livraison',
        'SELECT_DATE' : 'sélectionner une date',
        'STAFF' : 'Le personnel',
        'ORDERS_MANAGER' : 'Le l\'ordre manager',
        'ORDERS_PROCESSOR' : 'Le l\'ordre Maître',
        'DELIVERY_MANAGER' : 'La livraison manager',
        'SEARCH_ORDER' : 'Un ordre de recherche',
        'SURNAME_OF_CUSTOMER' : 'Nom du client',
        'SURNAME_OF_RECEIVER' : 'Nom du destinataire',
        'DELIVERY_DATE_AFTER' : 'Date de livraison après',
        'CHOOSE_DATE' : 'Choisissez une date',
        'DELIVERY_DATE_BEFORE' : 'Date de livraison avant',
        'SEARCH' : 'Chercher',
        'HISTORY_OF_THE_ORDER' : 'Histoire de l\'ordre',
        'DATE' : 'La datte',
        'NEW_STATUS' : 'Nouveau statut',
        'WHO_CHANGED' : 'Qui changé',
        'CHANGING_STATUS' : 'Modification de l\'état',
        'CHANGE' : 'Changer',
        'YOUR_COMMENT' : 'votre commentaire',
        'Add Contact' : 'Ajouter le contact',
        'Edit Contact' : 'Modifier le contact',
        'Add new contact' : 'Ajouter',
        'Save contact' : 'Sauvegarder',
        'Add phone' : 'Ajouter le téléphone',
        'Search Contact' : 'Recherche contact',
        'Search contact' : 'Chercher',
        'BIRTHDAY_BEFORE' : 'Anniversaire avant',
        'BIRTHDAY_AFTER' : 'Anniversaire après',
        'EXACT_DATE' : 'Date exacte',
        'YEAR' : 'Las année',
        'MONTH' : 'Le mois',
        'DAY' : 'Jour',
        'Orders manager' : 'le l\'ordre manager',
        'Orders processor' : 'le l\'ordre Maître',
        'Delivery manager' : 'la livraison manager',
        'Admin' : 'administrateur',
        'Role orders manager' : 'le l\'ordre manager',
        'Role orders processor' : 'le l\'ordre Maître',
        'Role delivery manager' : 'la livraison manager',
        'Role admin' : 'administrateur',
        'Add User' : 'Ajouter l\'utilisateur',
        'Add' : 'Ajouter'
    }

};
