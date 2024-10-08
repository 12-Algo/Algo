def solution(n, tops):
    answer = 0
    dp = [1, 0]
    for i in range(1, 2*n+1):
        alone, twin = dp[0], dp[1]
        dp[0] = (alone+twin)%10007
        dp[1] = alone
        if(i%2!=0):
            if(tops[i//2] == 1):
                dp[1] += (alone+twin)%10007
    answer = (dp[0] + dp[1])%10007
    return answer