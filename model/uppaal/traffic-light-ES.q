//This file was generated from (Academic) UPPAAL 4.1.4 (rev. 5535), March 2014

/*

*/
control: A[] not (queue[S][0] == L && ((carLight[W] == GREEN && queue[W][0] == U) || carLight[E] == GREEN && (queue[E][0] == L || queue[E][0] == U)))
