//This file was generated from (Academic) UPPAAL 4.1.4 (rev. 5535), March 2014

/*

*/
control: A[] not ((CarGeneratorSouth.Go && queue[S][0] == L && ((CarGeneratorWest.Go && queue[W][0] == U) || (CarGeneratorEast.Go && (queue[E][0] == L || queue[E][0] == U)) )) ||\
(CarGeneratorSouth.Go && queue[S][0] == R && CarGeneratorWest.Go && queue[W][0] == U) ||\
(CarGeneratorWest.Go && queue[W][0] == U && ((CarGeneratorEast.Go && queue[E][0] == L) || (CarGeneratorSouth.Go && (queue[S][0] == L || queue[S][0] == R)))) ||\
(CarGeneratorWest.Go && queue[W][0] == R && CarGeneratorEast.Go && queue[E][0] == L) ||\
(CarGeneratorEast.Go && queue[E][0] == L && ((CarGeneratorSouth.Go && queue[S][0] == L) || (CarGeneratorWest.Go && (queue[W][0] == U || queue[W][0] == R)))) ||\
(CarGeneratorEast.Go && queue[E][0] == U && CarGeneratorSouth.Go && queue[S][0] == L))

/*

*/
control: A[] not (PedestrianGeneratorEast.Cross && (CarGeneratorEast.Go || (CarGeneratorWest.Go && queue[W][0] == U) || (CarGeneratorSouth.Go && queue[S][0] == R)))

/*

*/
control: A[] not (PedestrianGeneratorEast.Broken)

/*

*/
control: A[] not (CarGeneratorEast.Broken || CarGeneratorSouth.Broken || CarGeneratorWest.Broken)

/*

*/
control: A[] not (((CarGeneratorSouth.Go && queue[S][0] == L && ((CarGeneratorWest.Go && queue[W][0] == U) || (CarGeneratorEast.Go && (queue[E][0] == L || queue[E][0] == U)) )) ||\
(CarGeneratorSouth.Go && queue[S][0] == R && CarGeneratorWest.Go && queue[W][0] == U) ||\
(CarGeneratorWest.Go && queue[W][0] == U && ((CarGeneratorEast.Go && queue[E][0] == L) || (CarGeneratorSouth.Go && (queue[S][0] == L || queue[S][0] == R)))) ||\
(CarGeneratorWest.Go && queue[W][0] == R && CarGeneratorEast.Go && queue[E][0] == L) ||\
(CarGeneratorEast.Go && queue[E][0] == L && ((CarGeneratorSouth.Go && queue[S][0] == L) || (CarGeneratorWest.Go && (queue[W][0] == U || queue[W][0] == R)))) ||\
(CarGeneratorEast.Go && queue[E][0] == U && CarGeneratorSouth.Go && queue[S][0] == L)) ||\
(PedestrianGeneratorEast.Cross && (CarGeneratorEast.Go || (CarGeneratorWest.Go && queue[W][0] == U) || (CarGeneratorSouth.Go && queue[S][0] == R))) ||\
(PedestrianGeneratorEast.Broken) ||\
(CarGeneratorEast.Broken || CarGeneratorSouth.Broken || CarGeneratorWest.Broken))
