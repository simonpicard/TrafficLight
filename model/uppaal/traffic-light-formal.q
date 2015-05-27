/*1*/
PedestrianGeneratorEast.PushButton -->PedestrianGeneratorEast.Cross
/*2*/
A[] not (pedestrianLight == GREEN && (carLight[E] == GREEN || (carLight[W] == GREEN && queue[W][0] == U) || (carLight[S] == GREEN && queue[S][0] == R)))
/*2-BIS*/
A[] not (PedestrianGeneratorEast.Cross && (CarGeneratorEast.CarCrossing || CarGeneratorWest.CarCrossing && queue[W][0] == U || CarGeneratorSouth.CarCrossing && queue[S][0]==R))
/*3.1*/
(CarGeneratorEast.AcceptCar&&queueIndex[E]!=0) --> CarGeneratorEast.CarCrossing
/*3.2*/
(CarGeneratorSouth.AcceptCar&&queueIndex[S]!=0) --> CarGeneratorSouth.CarCrossing
/*3.3*/
(CarGeneratorWest.AcceptCar&&queueIndex[W]!=0) --> CarGeneratorWest.CarCrossing
/*4*/
A[] not ((carLight[S] == GREEN && queue[S][0] == L && ((carLight[W] == GREEN && queue[W][0] == U) || (carLight[E] == GREEN && (queue[E][0] == L || queue[E][0] == U)) )) ||
(carLight[S] == GREEN && queue[S][0] == R && carLight[W] == GREEN && queue[W][0] == U) ||
(carLight[W] == GREEN && queue[W][0] == U && ((carLight[E] == GREEN && queue[E][0] == L) || (carLight[S] == GREEN && (queue[S][0] == L || queue[S][0] == R)))) ||
(carLight[W] == GREEN && queue[W][0] == R && carLight[E] == GREEN && queue[E][0] == L) ||
(carLight[E] == GREEN && queue[E][0] == L && ((carLight[S] == GREEN && queue[S][0] == L) || (carLight[W] == GREEN && (queue[W][0] == U || queue[W][0] == R)))) ||
(carLight[E] == GREEN && queue[E][0] == U && carLight[S] == GREEN && queue[S][0] == L))
/*4-BIS*/
A[] not (CarGeneratorEast.CarCrossing && ((CarGeneratorSouth.CarCrossing && queue[S][0] == L) || (queue[E][0])==L && CarGeneratorWest.CarCrossing) || CarGeneratorWest.CarCrossing && CarGeneratorSouth.CarCrossing)
/*5*/
A[] not deadlock