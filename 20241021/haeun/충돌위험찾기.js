function solution(points, routes) {
    let count = 0;

    const memo = {};
    let curTime = 0;

    for (const route of routes) {
        curTime = 0;
        let start;
        let end;
        for (let i = 1; i < route.length; i++) {
            start = points[route[i - 1] - 1];
            end = points[route[i] - 1];

            simulate(start, end);
        }
        if (memo[curTime]) memo[curTime].push(`${end[0]} ${end[1]}`)
        else memo[curTime] = [`${end[0]} ${end[1]}`];


    }

    for (const mkey in memo) {
        const map = {};
        for (const location of memo[mkey]) {
            map[location] = (map[location] || 0) + 1;
        }

        for (const key in map) {
            if (map[key] > 1) {
                count++;
            }
        }
    }



    function simulate(start, end) {
        if (start[0] === end[0] && start[1] === end[1]) {
            curTime++;

            return;
        }
        else if (start[0] < end[0]) {
            for (let i = start[0]; i < end[0]; i++) {

                if (memo[curTime]) memo[curTime].push(`${i} ${start[1]}`)
                else memo[curTime] = [`${i} ${start[1]}`];

                curTime++;
            }
        }
        else {
            for (let i = start[0]; i > end[0]; i--) {
                if (memo[curTime]) memo[curTime].push(`${i} ${start[1]}`)
                else memo[curTime] = [`${i} ${start[1]}`];

                curTime++;
            }
        }


        if (start[1] < end[1]) {
            for (let j = start[1]; j < end[1]; j++) {
                if (memo[curTime]) memo[curTime].push(`${end[0]} ${j}`)
                else memo[curTime] = [`${end[0]} ${j}`];

                curTime++;
            }
        }
        else {
            for (let j = start[1]; j > end[1]; j--) {
                if (memo[curTime]) memo[curTime].push(`${end[0]} ${j}`)
                else memo[curTime] = [`${end[0]} ${j}`];

                curTime++;
            }

        }

    }
    return count;
}