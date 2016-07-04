# SvgToElm 

Convert a SVG XML document into Elm Svg expressions.

## Usage Example

Given the file `demo.svg`

```xml
<svg height="210" width="500">
  <polygon points="100,10 40,198 190,78 10,78 160,198" style="fill:lime;stroke:purple;stroke-width:5;fill-rule:nonzero;"/>
</svg>
```

execute

```
java -jar target/svg-to-elm-1.0-SNAPSHOT.jar < demo.svg > demo.elm
```

for producing the file `demo.elm`

```haskell
svg [
    height "210"
  , width "500"] [
    polygon [
      points "100,10 40,198 190,78 10,78 160,198"
    , Svg.Attributes.style "fill:lime;stroke:purple;stroke-width:5;fill-rule:nonzero;"] []
]
```

## Built With

* Java 
* Maven 

## Authors

* [Massimo Zaniboni](https://github.com/massimo-zaniboni)

## License

This project is licensed under GPLv3+.

