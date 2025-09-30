/**
 * The controller is a JavaScript function that augments the AngularJS scope and exposes functions that can be used in the custom widget template
 * 
 * Custom widget properties defined on the right can be used as variables in a controller with $scope.properties
 * To use AngularJS standard services, you must declare them in the main function arguments.
 * 
 * You can leave the controller empty if you do not need it.
 */
function ($scope) {
    var white = 'white';
    
    // add a new variable in AngularJS scope. It'll be usable in the template directly with {{ backgroudColor }} 
    $scope.backgroudColor = white;
    
    // define a function to be used in template with ctrl.toggleBackgroundColor()
    this.toggleBackgroundColor = function() {
        if ($scope.backgroudColor === white) {
           // use the custom widget property backgroudColor with $scope.properties.backgroudColor
            $scope.backgroudColor = $scope.properties.background;
        } else {
            $scope.backgroudColor = white;
        }
    };
}