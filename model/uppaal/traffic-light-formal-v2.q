/*
Liveness property 1
*/
PedestrianGeneratorEast.PushButton -->PedestrianGeneratorEast.Cross

/*
Liveness property 2.a
*/
(CarGeneratorEast.AcceptCar&&queueIndex[E]!=0) --> CarGeneratorEast.CarCrossing

/*
Liveness property 2.b
*/
(CarGeneratorSouth.AcceptCar&&queueIndex[S]!=0) --> CarGeneratorSouth.CarCrossing

/*
Liveness property 2.c
*/
(CarGeneratorWest.AcceptCar&&queueIndex[W]!=0) --> CarGeneratorWest.CarCrossing

/*
Liveness property 3
*/
A[] not deadlock

/*
Safety property 1
*/
A[] not (pedestrianLight == GREEN && (carLight[E] == GREEN || (carLight[W] == GREEN && queue[W][0] == U) || (carLight[S] == GREEN && queue[S][0] == R)))

/*
Safety property 2
*/
A[] not (PedestrianGeneratorEast.Cross && (CarGeneratorEast.CarCrossing || CarGeneratorWest.CarCrossing && queue[W][0] == U || CarGeneratorSouth.CarCrossing && queue[S][0]==R))

/*
Safety property 3
*/
A[] not ((carLight[S] == GREEN && queue[S][0] == L && ((carLight[W] == GREEN && queue[W][0] == U) || (carLight[E] == GREEN && (queue[E][0] == L || queue[E][0] == U)) )) ||\
(carLight[S] == GREEN && queue[S][0] == R && carLight[W] == GREEN && queue[W][0] == U) ||\
(carLight[W] == GREEN && queue[W][0] == U && ((carLight[E] == GREEN && queue[E][0] == L) || (carLight[S] == GREEN && (queue[S][0] == L || queue[S][0] == R)))) ||\
(carLight[W] == GREEN && queue[W][0] == R && carLight[E] == GREEN && queue[E][0] == L) ||\
(carLight[E] == GREEN && queue[E][0] == L && ((carLight[S] == GREEN && queue[S][0] == L) || (carLight[W] == GREEN && (queue[W][0] == U || queue[W][0] == R)))) ||\
(carLight[E] == GREEN && queue[E][0] == U && carLight[S] == GREEN && queue[S][0] == L))

/*
Safety property 4
*/
A[] not ((CarGeneratorSouth.CarCrossing && queue[S][0] == L && ((CarGeneratorWest.CarCrossing && queue[W][0] == U) || (CarGeneratorEast.CarCrossing && (queue[E][0] == L || queue[E][0] == U)) )) ||\
(CarGeneratorSouth.CarCrossing && queue[S][0] == R && CarGeneratorWest.CarCrossing && queue[W][0] == U) ||\
(CarGeneratorWest.CarCrossing && queue[W][0] == U && ((CarGeneratorEast.CarCrossing && queue[E][0] == L) || (CarGeneratorSouth.CarCrossing && (queue[S][0] == L || queue[S][0] == R)))) ||\
(CarGeneratorWest.CarCrossing && queue[W][0] == R && CarGeneratorEast.CarCrossing && queue[E][0] == L) ||\
(CarGeneratorEast.CarCrossing && queue[E][0] == L && ((CarGeneratorSouth.CarCrossing && queue[S][0] == L) || (CarGeneratorWest.CarCrossing && (queue[W][0] == U || queue[W][0] == R)))) ||\
(CarGeneratorEast.CarCrossing && queue[E][0] == U && CarGeneratorSouth.CarCrossing && queue[S][0] == L))
