//This file was generated from (Academic) UPPAAL 4.1.4 (rev. 5535), March 2014

/*
Pas de collisions entre les voitures
*/
control: A[] not ((CarGeneratorSouth.Go && queue[S][0] == L && ((CarGeneratorWest.Go && queue[W][0] == U) || (CarGeneratorEast.Go && (queue[E][0] == L || queue[E][0] == U)) )) ||\
(CarGeneratorSouth.Go && queue[S][0] == R && CarGeneratorWest.Go && queue[W][0] == U) ||\
(CarGeneratorWest.Go && queue[W][0] == U && ((CarGeneratorEast.Go && queue[E][0] == L) || (CarGeneratorSouth.Go && (queue[S][0] == L || queue[S][0] == R)))) ||\
(CarGeneratorWest.Go && queue[W][0] == R && CarGeneratorEast.Go && queue[E][0] == L) ||\
(CarGeneratorEast.Go && queue[E][0] == L && ((CarGeneratorSouth.Go && queue[S][0] == L) || (CarGeneratorWest.Go && (queue[W][0] == U || queue[W][0] == R)))) ||\
(CarGeneratorEast.Go && queue[E][0] == U && CarGeneratorSouth.Go && queue[S][0] == L))

/*
Pas de collision entre voitures et pi\u00e9tons
*/
control: A[] not (PedestrianGeneratorEast.Cross && (CarGeneratorEast.Go || (CarGeneratorWest.Go && queue[W][0] == U) || (CarGeneratorSouth.Go && queue[S][0] == R)))

/*
Ne pas atteindre l'\u00e9tat broken, donc eviter une starvation des pi\u00e9tons
*/
control: A[] not (PedestrianGeneratorEast.Broken)

/*
Ne pas atteindre l'\u00e9tat broken, donc eviter une starvation des voitures
*/
control: A[] not (CarGeneratorEast.Broken || CarGeneratorSouth.Broken || CarGeneratorWest.Broken)

/*
Gros OR de tout les \u00e9tats d'\u00e9chec => wining condition
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
