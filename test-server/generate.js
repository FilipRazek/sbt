const { writeFile } = require("fs");
const { faker } = require("@faker-js/faker");

const LENGTH = 100_000;
const OUT_FILE_JSON = "db.json";
const OUT_FILE_CSV = "db.csv";

const users = createRandomUsers(LENGTH);

writeUsersToJson(users, OUT_FILE_JSON);
writeUsersToCsv(users, OUT_FILE_CSV);

function writeUsersToCsv(users, csvFile) {
  const userStrings = users.map(stringifyUser);
  writeToFile(csvFile, userStrings.join("\n"));
}

function writeUsersToJson(users, jsonFile) {
  writeToFile(jsonFile, JSON.stringify({ users }, null, 4));
}

function writeToFile(fileName, data) {
  writeFile(fileName, data, (err) => {
    if (err) {
      console.log(err);
    }
  });
}

function stringifyUser({ name, cardId }) {
  return [name, cardId].join(",");
}

function createRandomUsers(length) {
  return Array.from({ length }, createRandomUser);
}

function createRandomUser() {
  return {
    name: faker.person.fullName(),
    cardId: faker.finance.creditCardNumber("####-####-####-###L"),
  };
}
