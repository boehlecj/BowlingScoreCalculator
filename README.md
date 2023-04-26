# BowlingScoreCalculator
###BowlingScoreCalculator assists in computing valid bowling scores from an array of individual rolls.
 * Normal frames will include a pair of integers such as [5, 2]
 * Spare frames will include an integer and a spare symbol "/" such as [9, "/"]
 * Strike frames will include only one symbol ["X"]

###Frame data will be validated in accordance with current accepted bowling rules. Any frame score which exceeds the boundary of this scoring model will result in an InvalidBowlingScoreException:
 * NormalFrame: 9 pins or less
 * SpareFrame: 20 pins or less
 * StrikeFrame: 30 pins or less

###Invalid roll sequences may also result in an error. If a spare symbol "/" follows a strike symbol "X" (which does not exist in the bowling universe) the result will be an exception.