internal class Candy {
    fun candy(ratings: IntArray): Int {
        var numberOfCandies = ratings.size
        var latestRatingIncIdx = 0
        var latestRatingDecIdx = 0
        var ratingIncChainLength = 0
        var prevRating = ratings[0]

        for (i in 1 until ratings.size) {
            if (ratings[i] > prevRating) {
                latestRatingIncIdx = i

                ratingIncChainLength = latestRatingIncIdx - latestRatingDecIdx + 1

                numberOfCandies += ratingIncChainLength - 1
            } else if (ratings[i] == prevRating) {
                latestRatingIncIdx = i
                latestRatingDecIdx = i
                ratingIncChainLength = 0
            } else {
                latestRatingDecIdx = i

                val ratingDecChainLength = latestRatingDecIdx - latestRatingIncIdx + 1

                val increase =
                    if (ratingDecChainLength > ratingIncChainLength) latestRatingDecIdx - latestRatingIncIdx
                    else latestRatingDecIdx - latestRatingIncIdx - 1
                numberOfCandies += increase
            }

            prevRating = ratings[i]
        }

        return numberOfCandies
    }
}