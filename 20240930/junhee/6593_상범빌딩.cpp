#include <iostream>
#include <vector>
#include <string>
#include <queue>

using namespace std;

int l, r, c;
int map[30][30][30];
int x, y, z;
int a, b, d;
int dx[6] = {0, 1, 0, -1, 0, 0};
int dy[6] = {1, 0, -1, 0, 0, 0};
int dz[6] = {0, 0, 0, 0, 1, -1};

struct coord
{
    int x, y, z;
};

void bfs()
{
    queue<coord> q;
    q.push({x, y, z});
    while (!q.empty())
    {
        coord cur = q.front();
        q.pop();
        int cost = map[cur.z][cur.x][cur.y];
        for (int i = 0; i < 6; i++)
        {
            int nx = cur.x + dx[i];
            int ny = cur.y + dy[i];
            int nz = cur.z + dz[i];
            if (0 <= nx && nx < r && 0 <= ny && ny < c && 0 <= nz && nz < l)
            {
                if (map[nz][nx][ny] == 0)
                {
                    map[nz][nx][ny] = cost + 1;
                    q.push({nx, ny, nz});
                }
            }
        }
    }
}

int main()
{
    while (true)
    {
        cin >> l >> r >> c;
        if (l == 0 && r == 0 && c == 0)
            break;
        for (int i = 0; i < l; i++)
        {
            for (int j = 0; j < r; j++)
            {
                string s;
                cin >> s;
                for (int k = 0; k < c; k++)
                {
                    if (s[k] == 'S')
                    {
                        x = j;
                        y = k;
                        z = i;
                        map[i][j][k] = 0;
                    }
                    if (s[k] == '.')
                    {
                        map[i][j][k] = 0;
                    }
                    if (s[k] == '#')
                    {
                        map[i][j][k] = -1;
                    }
                    if (s[k] == 'E')
                    {
                        a = j;
                        b = k;
                        d = i;
                        map[i][j][k] = 0;
                    }
                }
            }
        }
        bfs();
        if (map[d][a][b] == 0)
            cout << "Trapped!" << "\n";
        else
            cout << "Escaped in " << map[d][a][b] << " minute(s)." << endl;
    }
}
