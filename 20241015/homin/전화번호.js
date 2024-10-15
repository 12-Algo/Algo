// class Node {
//   constructor(value = "") {
//     this.children = new Map();
//     this.value = value;
//   }
// }

// class Trie {
//   constructor() {
//     this.rootNode = new Node();
//   }

//   insert(value) {
//     let currentNode = this.rootNode;
//     for (const char of value) {
//       const isHas = currentNode.children.has(char); // children이 Map객체이기때문에
//       if (!isHas) {
//         const nextString = currentNode.value + char;
//         const nextCharNode = new Node(nextString);
//         currentNode.children.set(char, nextCharNode);
//       }

//       currentNode = currentNode.children.get(char);
//     }
//   }
//   has(string) {
//     let currentNode = this.rootNode;
//     for (const char of string) {
//       if (!currentNode.children.get(char)) {
//         return false;
//       }
//       currentNode = currentNode.children.get(char);
//     }
//     return true;
//   }
// }

const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [testCase, ...arr] = input;
  let index = 0;
  for (let i = 0; i < Number(testCase); i++) {
    // const trie = new Trie();
    const arr_length = Number(arr[index]);
    const temp = [];
    let flag = false;
    for (let j = index + 1; j < index + arr_length + 1; j++) {
      temp.push(arr[j]);
    }
    temp.sort();
    for (let i = 1; i < temp.length; i++) {
      if (temp[i].startsWith(temp[i - 1])) {
        flag = true;
        break;
      }
    }

    // for (const now of temp) {
    //   if (trie.has(now)) {
    //     flag = true;
    //     break;
    //   } else {
    //     trie.insert(now);
    //   }
    // }
    flag ? console.log("NO") : console.log("YES");
    index += arr_length + 1;
  }
});
