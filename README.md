# scrolling-animation-compose
상하좌우로 흐르는 Compose용 Animation Component Package

<br/>

# 적용 예시
### horizontal
<div>
  <img width="400" src="https://github.com/nyangzzi/scrolling-animation-compose/assets/52737339/4fc8aa06-b11d-4785-a957-bafbea35e609"/>
  &nbsp;&nbsp;
  <img width="400" src="https://github.com/nyangzzi/scrolling-animation-compose/assets/52737339/57ca5eb8-26d7-4bc0-a844-17f15a52e747"/>
</div>

<br/>

### vertical
<div>
  <img width="400" src="https://github.com/nyangzzi/scrolling-animation-compose/assets/52737339/92993db3-b7e3-4d06-84a9-cb6dd5385762"/>
  &nbsp;&nbsp;
  <img width="400" src="https://github.com/nyangzzi/scrolling-animation-compose/assets/52737339/2530a604-dbcd-4ded-998a-662bf17ff7f5"/>
</div>

<br/><br/>

# How to use
자세한 내용은 [kdoc](https://nyangzzi.github.io/scrolling-animation-compose/) 문서를 확인하세요
<br/>

|@param|Type|
|:---|:---|
|modifier|Modifier|
|shape|Shape|
|deceleration|Float|
|direction|VerticalDirection or HorizontalDirection|
|content|@Composable BoxScope.() -> Unit|


### 좌우로 흐르는 텍스트
```kotlin
@Composable
fun ScrollingHorizontal(
    modifier : Modifier = Modifier,
    shape: Shape = RectangleShape,
    deceleration: Float = 15f,
    direction: HorizontalDirection = HorizontalDirection.LeftToRight,
    content: @Composable BoxScope.() -> Unit
)
```
### 위아래로 흐르는 텍스트
```kotlin
@Composable
fun ScrollingVertical(
    modifier : Modifier = Modifier,
    shape: Shape = RectangleShape,
    deceleration: Float = 15f,
    direction: VerticalDirection = VerticalDirection.TopToBottom,
    content: @Composable BoxScope.() -> Unit
)
```

