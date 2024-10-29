function solution(bandage, health, attacks) {
    const lastAttack = attacks.at(-1)[0];
    const damages = new Array(lastAttack + 1);
    const maxHealth = health;

    for (const [time, damage] of attacks) {
        damages[time] = damage;
    }

    let count = 0;
    for (let ct = 1; ct <= lastAttack; ct++) {
        if (health <= 0) return -1;
        if (damages[ct]) {
            health -= damages[ct];
            count = 0;
            continue;
        }
        count++;
        health += bandage[1];
        if (count === bandage[0]) {
            count = 0;
            health += bandage[2];
        }
        if (health > maxHealth) health = maxHealth;
    }



    return health === 0 ? -1 : health;
}

