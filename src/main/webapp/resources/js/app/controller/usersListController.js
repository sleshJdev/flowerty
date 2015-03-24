/**
 * Created by Катерина on 24.03.2015.
 */

app.controller('usersListController', function($scope, $http) {
    $scope.users = {

    };
    $scope.users.usersList = [
        {
            firstName : 'Alex',
            secondName : 'Brown',
            nameByFather : 'Nikolaevich',
            login: 'admin',
            role : 'admin'
        },
        {
            firstName : 'Lena',
            secondName : 'Pit',
            nameByFather : 'Alexandrovna',
            login: 'lenappp',
            role : 'manager'
        },
        {
            firstName : 'Gabriel',
            secondName : 'Maht',
            nameByFather : 'Valentinovich',
            login: 'mahtg',
            role : 'manager'
        }
    ];
});