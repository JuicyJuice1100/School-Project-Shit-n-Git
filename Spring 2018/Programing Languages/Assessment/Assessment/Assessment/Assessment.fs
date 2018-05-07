namespace Assessment

module Hexigonal =
    let checkIfHexigonal x =
        let equation (y: int) = (sqrt (8.0 * (float y) + 1.0) + 1.0) / 4.0
        equation x - floor (equation x) = 0.0
    let hexigonalSeq x y =
        let hexigonalEquation (x: int) = 
            (2 * x * (2 * x - 1)) / 2
        seq {for n in x .. y -> hexigonalEquation n}