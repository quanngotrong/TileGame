# TileGame
Tile Game (Demo, chưa có giao diện gì lun)

Nếu chưa tải Java 15 hay JavaFX thì tải về nhé

+) JDK 15: https://www.oracle.com/java/technologies/javase-jdk15-downloads.html

+) JavaFX SDK: https://gluonhq.com/products/javafx/

Ủa cái JavaFX SDK chả hiểu sao không thấy bản 15, thử tải bản LTS 11.0.2 xem có được không.

Tải xong thì add cái Library vào Project Libraries là oke.

IntelliJ thì như này: https://www.jetbrains.com/help/idea/javafx.html#add-javafx-lib

Eclipse chắc cũng tương tự.


Cơ bản thì game sẽ có flow: Starting Scene -> Game State, nếu bấm P thì sẽ chuyển sang Pause State. Trong Game State có Player và Enemy, Player hiện chỉ có thể Attack bằng phím Space. Nếu Player đánh hết Enemy thì chuyển sang Victory State, nếu Player bị chết thì chuyển sang Game Over State.


Demo là như vậy, ý tưởng phát triển thêm: 

+, Thêm khả năng bắn đạn (cho Player và Enemy), với Player thì cần thêm Energy. Ý tưởng thiết kế đạn thì Class Bullets sẽ extends Class Creature, mỗi khi nhấn phím Ctrl sẽ tùy theo direction của Player mà set xMove, yMove,... Đạn có thể thêm vào ArrayList entitiesManager đã có, hoặc thêm vào ArrayList mới, tùy theo cái nào làm được thì làm. Đạn cũng sẽ có bounds để check Collision Bounds với Enemy, nếu Intersect thì remove đạn khỏi ArrayList và takeDamage cho Enemy.

+, Thêm điểm số, hoặc nếu phát triển theo hướng Survival thì cần thêm số quái giết được, bộ đếm thời gian, cứ tới mỗi mốc thì sẽ spawn ra Enemy. 

+, Enemy thay vì đứng im, chờ Player đi vào zone rồi mới đuổi theo Player thì có thể đi Random hướng để đi lung tung.

+, Thêm các State tương ứng với mức Easy, Medium, Hard, thêm các World mới. Về World có thể sử dụng Tiled (https://www.mapeditor.org/) để thiết kế cho đẹp.

+, Thêm Story Scene, NPC Dialogue, tạo cốt truyện cho Game, cái này chắc hơi thừa, rảnh thì làm quá.

Starting Scene:
![image](https://user-images.githubusercontent.com/38860847/111766841-90e25e80-88d8-11eb-8212-2b308d6b484b.png)

Game State:
![image](https://user-images.githubusercontent.com/38860847/111766888-a2c40180-88d8-11eb-99b6-9af9df61d6ca.png)

Victory State:
![image](https://user-images.githubusercontent.com/38860847/111766943-b4a5a480-88d8-11eb-9719-908d5190a863.png)

Pause State:
![image](https://user-images.githubusercontent.com/38860847/111767002-c5561a80-88d8-11eb-888b-a062c8502953.png)

Game Over State:
![image](https://user-images.githubusercontent.com/38860847/111767060-d7d05400-88d8-11eb-8965-437763b0d228.png)

