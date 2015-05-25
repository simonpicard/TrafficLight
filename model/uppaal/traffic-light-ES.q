//This file was generated from (Academic) UPPAAL 4.1.4 (rev. 5535), March 2014

/*

*/
control: A[] not (pedestrianLight == GREEN && (carLight[E] == GREEN || (carLight[W] == GREEN && queue[W][0] == U) || (carLight[S] == GREEN && queue[S][0] == R)))

/*

*/
control: A[] not (PedestrianGenerator.Broken)

/*

*/
control: A[] not ((carLight[S] == GREEN && queue[S][0] == L && ((carLight[W] == GREEN && queue[W][0] == U) || (carLight[E] == GREEN && (queue[E][0] == L || queue[E][0] == U)) )) ||\
(carLight[S] == GREEN && queue[S][0] == L && carLight[W] == GREEN && queue[W][0] == U) ||\
(carLight[W] == GREEN && queue[W][0] == U && ((carLight[E] == GREEN && queue[E][0] == L) || (carLight[S] == GREEN && (queue[S][0] == L || queue[S][0] == R)))) ||\
(carLight[W] == GREEN && queue[W][0] == R && carLight[E] == GREEN && queue[E][0] == L) ||\
(carLight[E] == GREEN && queue[E][0] == L && ((carLight[S] == GREEN && queue[S][0] == L) || (carLight[W] == GREEN && (queue[W][0] == U || queue[W][0] == R)))) ||\
(carLight[E] == GREEN && queue[E][0] == U && carLight[S] == GREEN && queue[S][0] == L))
