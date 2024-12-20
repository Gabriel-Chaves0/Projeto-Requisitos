import { BrowserRouter as Router } from "react-router-dom";
import AnimatedRoutes from "./Components/AnimatedRoutes.js";

function App() {
  return (
    <Router>
      <main className='App'>
        <AnimatedRoutes />
      </main>
    </Router>
  );
}

export default App;
