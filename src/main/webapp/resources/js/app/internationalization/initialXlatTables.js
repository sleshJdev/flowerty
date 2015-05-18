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
        'RESET_PASS' : 'Reset the password',
        'CREATE_PASS' : 'Create a password',
        'CONFIRM_PASS' : 'Confirm your password',
        'PASSWORDS_DONT_MATCH' : 'The passwords don\'t match',
        'CANCEL' : 'Cancel',
        'ROLE' : 'Role',
        'ROLES' : 'Roles',
        'CONTACT_AND_INFO' : 'CONTACT AND BASICS INFORMATION',
        'HOMETOWN' : 'HOMETOWN',
        'COMPANY_INFO': 'COMPANY',
        'FRENCH' : 'Français'
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
        'FULL_COST' : 'Суммарная стоимость:',
        'FORM_ORDER' : 'Оформить заказ',
        'MAKE_ORDER' : 'Сделайте заказ!',
        'ADD_BASKET' : 'Добавить в корзину',
        'REMOVE_BASKET' : 'Удалить из корзины',
        'PHONE_LIST_EMPTY' : 'Список телефонов пуст.',
        'CANT_LEAVE_EMPTY' : 'Пожалуйста, заполните это поле.',
        'RESET_PASS' : 'Сбросить пароль',
        'CREATE_PASS' : 'Установить пароль',
        'CONFIRM_PASS' : 'Подтвердите пароль',
        'PASSWORDS_DONT_MATCH' : 'Пароли не совпадают',
        'CANCEL' : 'Отмена',
        'ROLE': 'Роль',
        'ROLES' : 'Роли',
        'CONTACT_AND_INFO' : 'КОНТАКТ И ОСНОВНАЯ ИНФОРМАЦИЯ',
        'HOMETOWN' : 'РОДНОЙ ГОРОД',
        'COMPANY_INFO': 'КОМПАНИЯ',
        'FRENCH' : 'Français'
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
        'RESET_PASS' : 'Remettre le mot de passe',
        'CREATE_PASS' : 'Créer un mot de passe',
        'CONFIRM_PASS' : 'Confirmez le mot de passe',
        'PASSWORDS_DONT_MATCH' : 'Les mot de passe n\'égalèrent pas',
        'CANCEL' : 'Annuler',
        'ROLE': 'La role',
        'ROLES' : 'Les roles',
        'CONTACT_AND_INFO' : 'Le contact et l\'information principale',
        'HOMETOWN' : 'La ville matérnèle',
        'COMPANY_INFO': 'SOCIÉTÉ',
        'FRENCH' : 'Français'
    }

};
