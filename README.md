# cordova-plugin-compress



## HTML

```html
<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Security-Policy"
        content=" style-src 'self' 'unsafe-inline'; media-src *; img-src 'self' data: content:;">
        <meta name="format-detection" content="telephone=no">
        <meta name="msapplication-tap-highlight" content="no">
        <meta name="viewport" content="initial-scale=1, width=device-width, viewport-fit=cover">
        <meta name="color-scheme" content="light dark">
        <link rel="stylesheet" href="css/index.css">
    </head>
    <body>
        <div class="app">
            <button onclick="start()">Start</button>
           
        </div>
        <script src="cordova.js"></script>
        <script src="js/index.js"></script>

        <script>

            function start() {
                cordova.plugins.MLKitPlugin.startMLKit(
                    "single",
                    function(a){
                        console.log(a);
                    },
                    function(b){
                        console.log(b);
                    }
                )
            }
        </script>

    </body>
</html>


```
## javascript
```js
 function start() {
                cordova.plugins.MLKitPlugin.startMLKit(
                    "single",
                    function(a){
                        console.log(a);
                    },
                    function(b){
                        console.log(b);
                    }
                )
            }
```